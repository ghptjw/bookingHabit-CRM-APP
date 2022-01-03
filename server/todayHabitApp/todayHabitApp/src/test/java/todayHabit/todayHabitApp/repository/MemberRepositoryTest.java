package todayHabit.todayHabitApp.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.Male;
import todayHabit.todayHabitApp.domain.Member;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Transactional
class MemberRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member("회원1", "test1@naver.com", "19971109", Male.남, "01011112222");
        //when
        memberRepository.saveMember(member);
        //then
        assertEquals(member, memberRepository.findMemberById(member.getId()));
    }
}