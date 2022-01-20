package todayHabit.todayHabitApp.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.Male;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.schedule.Schedule;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ClassRepositoryTest {

    @Autowired
    ClassRepository classRepository;

    @Test
    public void 수업조사_날짜() throws Exception{
        //given
        Member member = new Member("회원1", "test1@naver.com", "19971109", Male.남, "01011112222","1111");
        //when
        List<Schedule> classByDate = classRepository.findClassByDate(LocalDate.now(), 168l, null);
        //then
        Assert.assertEquals(classByDate.get(0).getStartDay(),LocalDate.now());
    }
}