package todayHabit.todayHabitApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


@SpringBootTest
@Transactional
class ClassServiceTest {

    @Autowired
    ClassService classService;


    @Test
    public void 당일수업조사() throws Exception{
        //given
        List<DayClassDto> dayClassDto = classService.DayClass(LocalDate.now(), 5l, 112l);
        //when
        //then
        assertEquals(dayClassDto.size(), 0);
    }

    @Test
    public void 이전수업조사() throws Exception{
        //given
        List<DayClassDto> dayClassDto = classService.BeforeDayClass(LocalDate.now(), 5l, 111l);
        //when
        //then
        assertEquals(dayClassDto.size(), 0);
    }
}