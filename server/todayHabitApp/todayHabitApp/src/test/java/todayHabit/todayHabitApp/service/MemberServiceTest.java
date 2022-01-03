package todayHabit.todayHabitApp.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.Male;
import todayHabit.todayHabitApp.domain.Member;
import todayHabit.todayHabitApp.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;
    @Test
    public void 중복회원_조사() throws Exception{
        //given
        Member member = new Member("회원1", "homelala@naver.com", "19971109", Male.남, "01011112222");
        //when

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.joinMember(member);
        });
    }

    @Test
    public void 회원센터정보등록() throws Exception{
        //given
        Member member = new Member("회원1", "homa@naver.com", "19971109", Male.남, "01011112222");
        em.persist(member);
        em.flush();
        em.clear();
        //when
        memberService.updateCenterInfo("ft1333", member.getId());
        em.flush();
        em.clear();

        System.out.println(memberRepository.findMemberById(member.getId()).getGym().getSerialNumber());
        //then
        Assert.assertEquals(memberRepository.findMemberById(member.getId()).getGym().getSerialNumber(), "ft1333");
    }
}