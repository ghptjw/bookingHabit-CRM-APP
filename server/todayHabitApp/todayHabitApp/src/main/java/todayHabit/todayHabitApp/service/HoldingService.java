package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.holding.HoldingList;
import todayHabit.todayHabitApp.domain.holding.HoldingLocation;
import todayHabit.todayHabitApp.domain.holding.HoldingMembership;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.error.OutOfEndDayMembershipPeriodException;
import todayHabit.todayHabitApp.error.OutOfStartDayMembershipPeriodException;
import todayHabit.todayHabitApp.repository.HoldingListRepository;
import todayHabit.todayHabitApp.repository.HoldingRepository;
import todayHabit.todayHabitApp.repository.MemberClassRepository;
import todayHabit.todayHabitApp.repository.MemberOwnMembershipRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HoldingService {

    private final MemberClassRepository memberClassRepository;
    private final MemberOwnMembershipRepository memberOwnMembershipRepository;
    private final HoldingRepository holdingRepository;
    private final HoldingListRepository holdingListRepository;

    public int checkHoldingMembership(Long gymId, Long membershipId, LocalDate startDay, LocalDate endDay) {

        List<MemberClass> classList = memberClassRepository.findBetweenDate(gymId, startDay, endDay, membershipId);
        return classList.size();

    }

    @Transactional
    public String holdingMembership(Long membershipId, Long holdingMembershipId, LocalDate startDay, LocalDate endDay) throws Exception{
        /*
         * 1. 시작일이 회원권 종료기간보다 안쪽인지 확인
         * 2. 종료일이 회원권 종료기간보다 안쪽인지 확인
         * 3. 홀딩권 삭제 상태 변경 후 홀딩 리스트에 저장
         * 4. 홀딩 기간 만큼 회원권 증가
         * */
        MemberOwnMembership membershipInfo = memberOwnMembershipRepository.findByIdToHolding(membershipId);
        if (!membershipInfo.getEndDay().isAfter(startDay)) {
            throw new OutOfStartDayMembershipPeriodException();
        } else if (!membershipInfo.getEndDay().isAfter(endDay)) {
            throw new OutOfEndDayMembershipPeriodException();
        }else{
            Period period = startDay.until(endDay);
            HoldingMembership holdingMembershipInfo = holdingRepository.findById(holdingMembershipId);
            holdingMembershipInfo.changeDeleteValue();
            holdingMembershipInfo.setUsingPeriod(period.getDays());
            HoldingList holdingList = new HoldingList(holdingMembershipInfo, startDay, endDay, HoldingLocation.APP);
            holdingListRepository.save(holdingList);
            membershipInfo.increaseMembershipEndDay(period.getDays()+1);
            return "홀딩이 완료되었습니다.";
        }

    }
}
