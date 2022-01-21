package todayHabit.todayHabitApp.filebaseRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.Male;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.dto.member.MemberDto;
import todayHabit.todayHabitApp.firebase.FirebaseRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FirebaseRepositoryTest {

    @Autowired
    private FirebaseRepository firebaseRepository;

    @Test
    public void 파이어베이스_시작() throws Exception{
        //given
        
        //when
        firebaseRepository.initialize();
        //then
    }

}