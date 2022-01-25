package todayHabit.todayHabitApp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;
import todayHabit.todayHabitApp.service.ClassService;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping("/class/day/after")
    public List<DayClassDto> AfterDaySchedule(@RequestBody AfterDayDto request) throws Exception{
        return classService.DayClass(LocalDate.now(), request.getGymId(), request.getMembershipId());
    }

    @PostMapping("/class/day/before")
    public List<DayClassDto> BeforeDaySchedule(@RequestBody AfterDayDto request) throws Exception{
        return classService.BeforeDayClass(LocalDate.now(), request.getGymId(), request.getMembershipId());
    }

    @PostMapping("/class/reserve")
    public String reserveClass(@RequestBody reserveClassDto request) throws Exception{
        String result = classService.reserveClass(request.getMemberId(), request.getGymId(),
                request.getMembershipId(), request.getClassId());
        return result;
    }

    @PostMapping("/class/cancel")
    public String cancelClass(@RequestBody reserveClassDto request) throws Exception{
        String result = classService.cancelClass(request.getMemberId(), request.getGymId(),
                request.getMembershipId(), request.getClassId());
        return result;
    }


    @Data
    static class AfterDayDto {
        @NotNull
        private LocalDate date;
        @NotNull
        private Long gymId;
        @NotNull
        private Long membershipId;
    }

    @Data
    static class BeforeDayDto {
        @NotNull
        private LocalDate date;
        @NotNull
        private Long gymId;
        @NotNull
        private Long membershipId;
    }

    @Data
    static class reserveClassDto{
        @NotNull
        private Long gymId;
        @NotNull
        private Long memberId;
        @NotNull
        private Long membershipId;
        @NotNull
        private Long classId;

    }
}
