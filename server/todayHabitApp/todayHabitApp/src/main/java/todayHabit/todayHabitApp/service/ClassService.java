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
import todayHabit.todayHabitApp.domain.schedule.Schedule;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;
import todayHabit.todayHabitApp.error.AlreadyReserveClassException;
import todayHabit.todayHabitApp.error.MaxClassException;
import todayHabit.todayHabitApp.error.ReserveBlockException;
import todayHabit.todayHabitApp.error.TimeoutReserveException;
import todayHabit.todayHabitApp.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        return classRepository.findClassByDate(date, gymId, classTypeList).stream()
                .map(classList -> new DayClassDto(classList))
                .collect(Collectors.toList());
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
        // 2-2-1 회원권 차감
        // 2-2-2 대기 인원에 추가
        Gym gymInfo = gymRepository.findById(gymId);
        Member memberInfo = memberRepository.findMemberById(memberId);
        Schedule classInfo = classRepository.findById(classId);
        MemberOwnMembership membership = memberOwnMembershipRepository.findById(membershipId);
        List<MemberClass> memberClassList = memberClassRepository.findByMemberIdWithClassId(memberId, classId);
        List<WaitingMember> waitingMemberList = waitingMemberRepository.findByMemberIdWithClassId(memberId, classId);
        LocalDateTime startTime = LocalDateTime.of(classInfo.getStartDay(), classInfo.getStartTime());
        LocalDateTime reservableTime = startTime.minusMinutes(gymInfo.getReservableTime());
        if (LocalDateTime.now().isAfter(reservableTime)) { // 현재 시간이 예약 가능 시간 보다 지났을 때 예약 불가능
            throw new TimeoutReserveException();
        }else if(!memberClassList.isEmpty() || !waitingMemberList.isEmpty()) {
            throw new AlreadyReserveClassException();
        }else if (classInfo.getTotalReservation() <= classInfo.getReserveNumber()) { //예약 정원이 다찼을 때 -> 대기
            int waitingNumber;
            Optional<WaitingMember> maxWaitingNumber = waitingMemberRepository.findMaxWaitingNumber(classId);
            if (maxWaitingNumber.isEmpty()) {
                waitingNumber = 0;
            } else {
                waitingNumber = maxWaitingNumber.get().getWaitingNumber();
            }
            membership.increaseMembership();
            WaitingMember waitingMember = new WaitingMember(gymInfo, classInfo, memberInfo, membership, waitingNumber);
            return "대기 인원으로 등록 완료되었습니다.";
        } else { // 예약 가능
            membership.increaseMembership();
            classInfo.increaseCount();
            MemberClass memberClass = new MemberClass(gymInfo, classInfo, memberInfo, membership);
            memberClassRepository.save(memberClass);
            return "예약 인원으로 등록 완료되었습니다.";
        }
    }

}

