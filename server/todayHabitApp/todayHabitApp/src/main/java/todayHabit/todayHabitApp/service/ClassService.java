package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.ReserveBlock;
import todayHabit.todayHabitApp.domain.WaitingMember;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembershipClassType;
import todayHabit.todayHabitApp.domain.schedule.ClassHistory;
import todayHabit.todayHabitApp.domain.schedule.Schedule;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;
import todayHabit.todayHabitApp.error.*;
import todayHabit.todayHabitApp.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassService {
    private final MemberRepository memberRepository;
    private final ClassRepository classRepository;
    private final MemberOwnMembershipRepository memberOwnMembershipRepository;
    private final MemberClassRepository memberClassRepository;
    private final ReserveBlockRepository reserveBlockRepository;
    private final GymRepository gymRepository;
    private final WaitingMemberRepository waitingMemberRepository;
    private final ClassHistoryRepository classHistoryRepository;

    public List<DayClassDto> DayClass(LocalDate date, Long gymId, Long membershipId) throws Exception{
        List<ReserveBlock> reserveBlocks = reserveBlockRepository.findByStartDay(date);
        if(!reserveBlocks.isEmpty()){
            throw new ReserveBlockException();
        }
        List<Long> classTypeList = new ArrayList();
        MemberOwnMembership membership = memberOwnMembershipRepository.findById(membershipId);
        List<MemberOwnMembershipClassType> membershipClassTypes = membership.getMembershipClassTypes();
        for (MemberOwnMembershipClassType membershipClassType : membershipClassTypes) {
            classTypeList.add(membershipClassType.getClassType().getId());
        }
        List<DayClassDto> classLists = classRepository.findClassByDate(date, gymId, classTypeList).stream()
                .map(classList -> new DayClassDto(classList))
                .collect(Collectors.toList());
        for (DayClassDto dayClassDto : classLists) {
            List<MemberClass> classInfo = memberClassRepository.findByMemberIdWithClassId(membershipId, dayClassDto.getClassId());
            if (!classInfo.isEmpty()) { // 예약한 회원이라면
                dayClassDto.changeReserve();
            }else{
                LocalTime now = LocalTime.now();
                if (!now.isAfter(dayClassDto.getStartTime())) {
                    classLists.remove(dayClassDto);
                }
            }
        }
        return classLists;
    }

    public List<DayClassDto> BeforeDayClass(LocalDate date, Long gymId, Long membershipId) throws Exception{
        List<ReserveBlock> reserveBlocks = reserveBlockRepository.findByStartDay(date);
        if(!reserveBlocks.isEmpty()){
            throw new ReserveBlockException();
        }
        List<MemberClass> classLists = memberClassRepository.findByMemberOwnMembershipId(date, gymId, membershipId);
        return classLists.stream().map(classList -> new DayClassDto(classList))
                .collect(Collectors.toList());
    }

    @Transactional
    public String reserveClass(Long memberId, Long gymId, Long membershipId, Long classId) throws Exception {
        // 1. 예약 가능한 시간인지 확인
        // 1.1 이미 예약된 회원인지 확인
        // 2. 예약 정원이 있나 확인 -> 없으면 대기
        // 2-1. 예약 정원이 있으면 예약
        // 2-2-1 회원권 차감
        // 2-2-2 수업 정원 증가
        // 2-2-3 수업 인원 리스트 삽입
        // 2-2. 예약 정원이 없으면 대기 가능인원 확인
        // 2-2-0 센터 대기 가능 인원 세기
        // 2-2-1 회원권 차감
        // 2-2-2 대기 인원에 추가
        Gym gymInfo = gymRepository.findById(gymId);
        Member memberInfo = memberRepository.findMemberById(memberId);
        Schedule classInfo = classRepository.findById(classId);
        MemberOwnMembership membership = memberOwnMembershipRepository.findById(membershipId);
        List<MemberClass> memberClassList = memberClassRepository.findByMemberIdWithClassIdByDate(memberId, classId);
        List<WaitingMember> waitingMemberList = waitingMemberRepository.findByMemberIdWithClassId(memberId, classId);
        LocalDateTime reservableTime = LocalDateTime
                .of(classInfo.getStartDay(), classInfo.getStartTime())
                .minusMinutes(gymInfo.getReservableTime());
        if (LocalDateTime.now().isAfter(reservableTime)) { // 현재 시간이 예약 가능 시간 보다 지났을 때 예약 불가능
            throw new TimeoutReserveException();
        }else if(membership.getDayAttend() >= membership.getMembership().getMaxDayAttend()) {
            throw new OverDayAttendException();
        }else if(membership.getWeekAttend() >= membership.getMembership().getMaxWeekAttend()) {
            throw new OverWeekAttendException();
        }else if(!memberClassList.isEmpty() || !waitingMemberList.isEmpty()) { // 이미 예약된 회원일 때
            throw new AlreadyReserveClassException();
        }else if (classInfo.getTotalReservation() <= classInfo.getReserveNumber()) { //예약 정원이 다찼을 때 -> 대기
            int waitingNumber;
            Optional<Integer> maxWaitingNumber = waitingMemberRepository.findMaxWaitingNumber(classId);
            ClassHistory classHistory = new ClassHistory(gymInfo, classInfo, memberInfo, 3);
            classHistoryRepository.save(classHistory);
            if (maxWaitingNumber.isEmpty()) { 
                waitingNumber = 1;
            } else {
                waitingNumber = maxWaitingNumber.get().intValue();
            }
            if (waitingNumber >= gymInfo.getLimitWaitingMember()) {
                throw new MaxWaitingMemberException();
            }
            boolean checkAvailable = membership.increaseMembership(classInfo.getDecrease());
            if (!checkAvailable) { // 회원권이 모자를 때
                throw new NotEnoughMembershipException();
            }
            WaitingMember waitingMember = new WaitingMember(gymInfo, classInfo, memberInfo, membership, waitingNumber);
            waitingMemberRepository.save(waitingMember);
            membership.increaseAttend();
            return "대기 인원으로 등록 완료되었습니다.";
        } else { // 예약 가능
            ClassHistory classHistory = new ClassHistory(gymInfo, classInfo, memberInfo, 0);
            classHistoryRepository.save(classHistory);
            membership.increaseMembership(classInfo.getDecrease());
            classInfo.increaseCount();
            MemberClass memberClass = new MemberClass(gymInfo, classInfo, memberInfo, membership);
            memberClassRepository.save(memberClass);
            membership.increaseAttend();
            return "예약 인원으로 등록 완료되었습니다.";
        }
    }


    @Transactional
    public String cancelClass(Long memberId, Long gymId, Long membershipId, Long classId) {
        // 1.예약 취소 가능 시간인지 확인
        // 2-1 예약 회원
        // 2-1-1 예약 리스트 삭제
        // 2-1-2 회원권 복구
        // 2-1-2 수업 회원 수 복구
        // 2-1-3 대기 인원 번호 변경
        // 2-2 대기 회원
        // 2-2-1 회원권 복구
        // 2-2-2 대기 인원 번호 변경
        // 2-2-3 대기 리스트 삭제

        Gym gymInfo = gymRepository.findById(gymId);
        Member memberInfo = memberRepository.findMemberById(memberId);
        Schedule classInfo = classRepository.findById(classId);
        MemberOwnMembership membership = memberOwnMembershipRepository.findById(membershipId);
        List<MemberClass> memberClassList = memberClassRepository.findByMemberIdWithClassIdByDate(memberId, classId);
        List<WaitingMember> waitingMemberList = waitingMemberRepository.findByMemberIdWithClassId(memberId, classId);
        LocalDateTime ableCancelTime = LocalDateTime
                .of(classInfo.getStartDay(), classInfo.getStartTime())
                .minusMinutes(gymInfo.getCancelReserveTime());
        ClassHistory classHistory = new ClassHistory(gymInfo, classInfo, memberInfo, 4);
        classHistoryRepository.save(classHistory);
        if (LocalDateTime.now().isAfter(ableCancelTime)) { // 현재 시간이 예약 가능 시간 보다 지났을 때 예약 불가능
            throw new TimeoutCancelException();
        }else if(!memberClassList.isEmpty()) { //예약 회원일 때
            membership.decreaseMembership(classInfo.getDecrease());
            classInfo.decreaseCount();
            memberClassRepository.deleteById(memberClassList.get(0));
            List<WaitingMember> waitingList = waitingMemberRepository.findByClassId(classId);
            for (WaitingMember waitingMember : waitingList) {
                if (waitingMember.getWaitingNumber() == 1) {
                    classInfo.increaseCount();
                    MemberClass memberClass = new MemberClass(waitingMember.getGym(), waitingMember.getSchedule(),
                            waitingMember.getMember(), waitingMember.getMemberOwnMembership());
                    ClassHistory classWaitingHistory = new ClassHistory(waitingMember.getGym(), waitingMember.getSchedule(),
                            waitingMember.getMember(), 0);
                    waitingMemberRepository.deleteById(waitingMember);
                    classHistoryRepository.save(classWaitingHistory);
                    memberClassRepository.save(memberClass);
                }else{
                    waitingMember.changeWaitingNumber();
                }
            }
            membership.decreaseAttend();
            return "예약 취소가 완료되었습니다.";
        }else if (!waitingMemberList.isEmpty()) { //대기 회원일 때\
            membership.decreaseMembership(classInfo.getDecrease());
            waitingMemberRepository.deleteById(waitingMemberList.get(0));
            List<WaitingMember> waitingList =
                    waitingMemberRepository.findByBehindWaitingMember(classId, waitingMemberList.get(0).getWaitingNumber());
            for (WaitingMember waitingMember : waitingList) {
                waitingMember.changeWaitingNumber();
            }
            membership.decreaseAttend();
            return "대기 취소가 완료되었습니다.";
        }else{
            throw new IllegalStateException("예약이 안되어있는 회원입니다.");
        }
    }
}

