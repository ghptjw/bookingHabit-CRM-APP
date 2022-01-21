package todayHabit.todayHabitApp.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@Transactional
class ClassServiceTest {

    @Autowired
    ClassService classService;


    @Test
    public void 당일수업조사() throws Exception{
        //given
        //when
        List<DayClassDto> dayClassDto = classService.DayClass(LocalDate.now(), 5l, 112l);

        //then
        Assert.assertEquals(dayClassDto.size(), 0);
    }

    @Test
    public void 이전수업조사() throws Exception{
        //given

        //when
        List<DayClassDto> findClassList = classService.BeforeDayClass(LocalDate.now(), 5l, 111l);
        //then
        Assert.assertEquals(findClassList.size(), 1);
    }
}