package todayHabit.todayHabitApp.dto.schedule;

import lombok.Data;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.schedule.CoachClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MembershipClassListDto {

    private int count;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private List<ClassInfo> classList;

    public MembershipClassListDto(int count, LocalDate membershipStartDate, LocalDate membershipEndDate, List<MemberClass> classList) {
        this.count = count;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
        this.classList = classList.stream()
                .map(classLists -> new ClassInfo(classLists))
                .collect(Collectors.toList());
    }

    @Data
    static class ClassInfo{
        private Long id;
        private String classTypeName;
        private LocalDate startDate;
        private LocalTime startTime;
        private List<ClassCoachDto> coachName;

        public ClassInfo(MemberClass memberClass) {
            this.id = memberClass.getSchedule().getId();
            this.classTypeName = memberClass.getSchedule().getClassTypeName();
            this.startDate = memberClass.getSchedule().getStartDay();
            this.startTime = memberClass.getSchedule().getStartTime();
            this.coachName = memberClass.getSchedule().getCoachClasses()
                    .stream().map(coachClass -> new ClassCoachDto(coachClass))
                    .collect(Collectors.toList());
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


}
