package todayHabit.todayHabitApp.dto.member;

import lombok.Data;
import todayHabit.todayHabitApp.domain.gym.GymContainMember;
import todayHabit.todayHabitApp.domain.holding.HoldingMembership;
import todayHabit.todayHabitApp.domain.member.Male;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembershipClassType;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@Data
public class LoginMemberDto {

    private Long gymId;
    private Long member_id;
    private String name;
    private String email;
    private String birth;
    private Male male;
    private String phone;

    private List<MemberOwnMembershipsDto> memberOwnMemberships;
    private List<gymListDto> gymList;

    public LoginMemberDto(Member member) {
        this.gymId = member.getGym().getId();
        this.member_id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.birth = member.getBirth();
        this.male = member.getMale();
        this.phone = member.getPhone();
        this.memberOwnMemberships = member.getMemberOwnMemberships().stream()
                .map(memberOwnMembership -> new MemberOwnMembershipsDto(memberOwnMembership))
                .collect(toList());
        this.gymList = member.getGymList().stream()
                .map(gymList -> new gymListDto(gymList.getGym()))
                .collect(toList());
    }

    @Data
    private class gymListDto {
        private Long gymId;
        private String gymName;
        private int openReserveDate; // 예약이 몇일 전부터 열릴지(일 단위)
        private String openReserveTime; // 예약이 몇 시 부터 열릴지
        private boolean remainMember; // 잔여 인원 노출 여부

        private boolean pushAlarm; // 푸쉬 알람 여부
        private int pushAlarmTime; // 푸쉬 알림 시간(분단위)

        private int reservableTime; // 예약 가능 시간(분단위)

        private int changeReserveTime; // 예약 변경 가능 시간(분단위)

        private int cancelReserveTime; // 예약 취소 가능 시간(분단위)

        private int checkAttendTime; // 출석 가능 시간(분단위)
        private LocalTime lateTime; // 지각 기준 시간
        private boolean waitingReserve; // 예약 대기 인원 노출 여부
        private int reserveConfirmTime; // 예약 확정 시간(예약 가능 시간과 동일:1, 예약 변경 가능시간과 동일:2, 예약 취소 가능시간과 동일:3)
        private int limitWaitingMember; // 대기 가능 회원 제한

        public gymListDto(Gym gym) {
            this.gymId = gym.getId();
            this.gymName = gym.getName();
            this.openReserveDate = gym.getOpenReserveDate();
            this.openReserveTime = gym.getOpenReserveTime();
            this.remainMember = gym.isRemainMember();
            this.pushAlarm = gym.isPushAlarm();
            this.pushAlarmTime = gym.getPushAlarmTime();
            this.reservableTime = gym.getReservableTime();
            this.changeReserveTime = gym.getChangeReserveTime();
            this.cancelReserveTime = gym.getCancelReserveTime();
            this.checkAttendTime = gym.getCheckAttendTime();
            this.lateTime = gym.getLateTime();
            this.waitingReserve = gym.isWaitingReserve();
            this.reserveConfirmTime = gym.getReserveConfirmTime();
            this.limitWaitingMember = gym.getLimitWaitingMember();
        }
    }




}


