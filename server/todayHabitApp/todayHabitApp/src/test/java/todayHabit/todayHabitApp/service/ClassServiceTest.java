package todayHabit.todayHabitApp.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.schedule.Schedule;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;
import todayHabit.todayHabitApp.error.TimeoutReserveException;
import todayHabit.todayHabitApp.repository.ClassRepository;
import todayHabit.todayHabitApp.repository.MemberClassRepository;
import todayHabit.todayHabitApp.repository.MemberOwnMembershipRepository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


@SpringBootTest
@Transactional
class ClassServiceTest {

    @Autowired
    ClassService classService;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    MemberOwnMembershipRepository memberOwnMembershipRepository;
    @Autowired
    MemberClassRepository memberClassRepository;
    @Autowired
    EntityManager em;

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

    @Test
    public void 수업예약_예약성공() throws Exception{
        //given

        //when
        classService.reserveClass(193l,5l,111l,60673l);
        em.flush();
        em.clear();
        //then
        Schedule findClass = classRepository.findById(60673l);
        MemberOwnMembership findMembership = memberOwnMembershipRepository.findById(111l);
        List<MemberClass> memberClassList = memberClassRepository.findByMemberIdWithClassId(193l, 60673l);
        assertEquals(findClass.getReserveNumber(),1);
        assertEquals(findMembership.getCountClass(), 1);
        assertEquals(memberClassList.size(), 1);
    }

    @Test
    public void 수업예약_에약시간불가() throws Exception{
        //given
        //when
        //then
        Assert.assertThrows(TimeoutReserveException.class, () -> {
            classService.reserveClass(193l, 5l, 111l, 60674l);
        });
    }

    @Test
    public void 수업예약_대기인원() throws Exception{
        //given

        //when

        //then

    }

    @Test
    public void 수업예약_이미예약된회원() throws Exception{
        //given

        //when

        //then

    }
}