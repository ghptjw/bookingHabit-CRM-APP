package todayHabit.todayHabitApp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todayHabit.todayHabitApp.dto.schedule.AfterDayClassDto;
import todayHabit.todayHabitApp.dto.schedule.BeforeDayClassDto;
import todayHabit.todayHabitApp.service.ClassService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping("/class/day/after")
    public List<AfterDayClassDto> AfterDaySchedule(@RequestBody AfterDayDto request) {
        return classService.DayClass(LocalDate.now(), request.getGymId(), request.getMembershipId());
    }

    @PostMapping("/class/day/before")
    public List<BeforeDayClassDto> BeforeDaySchedule(@RequestBody AfterDayDto request) {
        return classService.BeforeDayClass(LocalDate.now(), request.getGymId(), request.getMembershipId());
    }
    @Data
    static class AfterDayDto {
        private LocalDate date;
        private Long gymId;
        private Long membershipId;
    }

    @Data
    static class BeforeDayDto {
        private LocalDate date;
        private Long gymId;
        private Long membershipId;
    }
}
