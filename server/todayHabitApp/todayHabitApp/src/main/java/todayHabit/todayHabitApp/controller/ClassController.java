package todayHabit.todayHabitApp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todayHabit.todayHabitApp.dto.DayClassDto;
import todayHabit.todayHabitApp.service.ClassService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping("/class/day")
    public List<DayClassDto> DaySchedule(@RequestBody DayDto request) {
        return classService.DayClass(LocalDate.now(), request.getGymId(), request.getMemberId());
    }

    @Data
    static class DayDto{
        private LocalDate date;
        private Long gymId;
        private Long memberId;
    }
}
