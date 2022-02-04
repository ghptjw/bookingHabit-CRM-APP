package todayHabit.todayHabitApp.dto.schedule;

import lombok.Data;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.schedule.CoachClass;
import todayHabit.todayHabitApp.domain.schedule.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class DayClassDto {

    private Long classId;
    private Long classTypeId;
    private String classTypeName;
    private int category;
    private LocalDate startDay;
    private LocalTime startTime;
    private int period;
    private int totalReservePerson;
    private int reservePerson;
    private int decrease;
    private String repeatDay;
    private String cycle;
    private boolean reserve;
    private List<ClassCoachDto> coachList;

    public DayClassDto(Schedule classList) {
        this.classId = classList.getId();
        this.classTypeId = classList.getClassType().getId();
        this.classTypeName = classList.getClassTypeName();
        this.category = classList.getCategory();
        this.startDay = classList.getStartDay();
        this.startTime = classList.getStartTime();
        this.period = classList.getPeriod();
        this.totalReservePerson = classList.getTotalReservation();
        this.reservePerson = classList.getReserveNumber();
        this.decrease = classList.getDecrease();
        this.repeatDay = classList.getRepeatDay();
        this.cycle = classList.getCycle();
        this.reserve = false;
        this.coachList = classList.getCoachClasses().stream()
                .map(coachInfo -> new ClassCoachDto(coachInfo))
                .collect(Collectors.toList());
    }

    public DayClassDto(MemberClass classList) {
        this.classId = classList.getSchedule().getId();
        this.classTypeId = classList.getSchedule().getClassType().getId();
        this.classTypeName = classList.getSchedule().getClassTypeName();
        this.category = classList.getSchedule().getCategory();
        this.startDay = classList.getSchedule().getStartDay();
        this.startTime = classList.getSchedule().getStartTime();
        this.period = classList.getSchedule().getPeriod();
        this.totalReservePerson = classList.getSchedule().getTotalReservation();
        this.reservePerson = classList.getSchedule().getReserveNumber();
        this.decrease = classList.getSchedule().getDecrease();
        this.repeatDay = classList.getSchedule().getRepeatDay();
        this.cycle = classList.getSchedule().getCycle();
        this.coachList = classList.getSchedule().getCoachClasses().stream()
                .map(coachInfo -> new ClassCoachDto(coachInfo))
                .collect(Collectors.toList());
    }

    public void changeReserve() {
        this.reserve = true;
    }

    @Data
    static class ClassCoachDto {
        private Long id;
        private String name;

        public ClassCoachDto(CoachClass coachClass) {
            this.id = coachClass.getCoach().getId();
            this.name = coachClass.getCoachName();
        }
    }
}
