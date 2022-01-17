package todayHabit.todayHabitApp.domain.gym;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Table(name = "gym")
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GYM_id")
    private Long id;

    private String name;
    private String location;
    private String logoImage;
    private String category;
    @Column(name = "adminName")
    private String adminName;
    private String email;
    private String phone;
    private String passwd;

    private int openReserveDate; // 예약이 몇일 전부터 열릴지(일 단위)
    private int openReserveType; // 예약이 열리는 시간 타입(일:1, 주:2)
    private String openReserveTime; // 예약이 몇 시 부터 열릴지
    private boolean remainMember; // 잔여 인원 노출 여부

    private boolean pushAlarm; // 푸쉬 알람 여부
    private int pushAlarmType;// 푸쉬알림 시간 타입(분:1, 시간:2, 일:3)
    private int pushAlarmTime; // 푸쉬 알림 시간(분단위)

    private int reservableType; // 예약 가능 시간 타입(분:1, 시간:2, 일:3)
    private int reservableTime; // 예약 가능 시간(분단위)

    private int changeReserveType; // 예약 변경 가능 시간 타입(분:1, 시간:2, 일:3)
    private int changeReserveTime; // 예약 변경 가능 시간(분단위)

    private int cancelReserveType; // 예약 취소 가능 시간 타입(분:1, 시간:2, 일:3)
    private int cancelReserveTime; // 예약 취소 가능 시간(분단위)

    private int checkAttendTime; // 출석 가능 시간(분단위)
    private LocalTime lateTime; // 지각 기준 시간
    private boolean waitingReserve; // 예약 대기 인원 노출 여부
    private int reserveConfirmTime; // 예약 확정 시간(예약 가능 시간과 동일:1, 예약 변경 가능시간과 동일:2, 예약 취소 가능시간과 동일:3)
    private int limitWaitingMember; // 대기 가능 회원 제한

    private int lockerCount; // 센터 락커 개수
    private int lockerPrice; // 센터 락커 가격

    private String subscribtionPath; // 센터 가입 경로
    private boolean approve; // 센터 본사 승인 여부
    @Enumerated(EnumType.STRING)
    private GymGrade grade;
    private String serialNumber; // 센터 고유 넘버


    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", logoImage='" + logoImage + '\'' +
                ", category='" + category + '\'' +
                ", adminName='" + adminName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
