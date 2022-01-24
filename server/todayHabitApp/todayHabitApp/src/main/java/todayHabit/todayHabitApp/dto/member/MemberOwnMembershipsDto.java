package todayHabit.todayHabitApp.dto.member;

import lombok.Data;
import todayHabit.todayHabitApp.domain.holding.HoldingMembership;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembershipClassType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class MemberOwnMembershipsDto {
    private Long id;
    private String name;
    private LocalDate startDay;
    private LocalDate endDay;
    private int countClass;
    private int payment;
    private int DayAttend;
    private int weekAttend;
    private LocalDateTime registerDate;
    private int maxCountClass;
    private boolean available;
    private List<MembershipClassTypeDto> membershipClassType;
    private List<HoldingMembershipDto> holdingMembershipDtoList;

    public MemberOwnMembershipsDto(MemberOwnMembership memberOwnMembership) {
        this.id = memberOwnMembership.getId();
        this.name = memberOwnMembership.getMembership().getName();
        this.startDay = memberOwnMembership.getStartDay();
        this.endDay = memberOwnMembership.getEndDay();
        this.countClass = memberOwnMembership.getCountClass();
        this.payment = memberOwnMembership.getPayment();
        this.DayAttend = memberOwnMembership.getDayAttend();
        this.weekAttend = memberOwnMembership.getWeekAttend();
        this.registerDate = memberOwnMembership.getRegisterDate();
        this.maxCountClass = memberOwnMembership.getMaxCountClass();
        this.available = memberOwnMembership.getAvailable();
        this.membershipClassType = memberOwnMembership.getMembershipClassTypes().stream()
                .map(membershipClassType -> new MembershipClassTypeDto(membershipClassType))
                .collect(toList());
        this.holdingMembershipDtoList = memberOwnMembership.getHoldingMemberships().stream()
                .map(holdingMembership -> new HoldingMembershipDto(holdingMembership))
                .collect(toList());
    }

    @Data
    static class MembershipClassTypeDto{
        private Long id;
        private String name;

        public MembershipClassTypeDto(MemberOwnMembershipClassType memberOwnMembershipClassType){
            this.id = memberOwnMembershipClassType.getId();
            this.name = memberOwnMembershipClassType.getClassTypeName();
        }
    }
    @Data
    private static class HoldingMembershipDto {
        private Long id;
        private Long membershipId;
        private int holdingPeriod;
        private int usingPeriod;

        public HoldingMembershipDto(HoldingMembership holdingMembership) {
            this.id = holdingMembership.getId();
            this.membershipId = holdingMembership.getMemberOwnMembership().getId();
            this.holdingPeriod = holdingMembership.getHoldingPeriod();
            this.usingPeriod = holdingMembership.getUsingPeriod();
        }
    }
}