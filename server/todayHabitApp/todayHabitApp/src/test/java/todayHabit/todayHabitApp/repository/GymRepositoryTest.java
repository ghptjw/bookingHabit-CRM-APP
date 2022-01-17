package todayHabit.todayHabitApp.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.gym.Gym;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GymRepositoryTest {

    @Autowired
    GymRepository gymRepository;
    @Test
    public void 센터정보_시리얼번호조회() throws Exception{
        //given
        //when
        Optional<Gym> findGym = gymRepository.findGymBySerialNumber("ft1333");
        //then
        Assert.assertEquals(findGym.get().getSerialNumber(),"ft1333");
    }
}