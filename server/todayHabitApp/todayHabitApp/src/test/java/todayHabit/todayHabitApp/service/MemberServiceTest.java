package todayHabit.todayHabitApp.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.Male;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.dto.member.LoginMemberDto;
import todayHabit.todayHabitApp.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    public void 중복회원_조사() throws Exception{
        //given
        Member member = new Member("회원1", "homelala@naver.com", "19971109", Male.남, "01011112222" ,"1111");

        //when

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.joinMember(member);
        });
    }
    
    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member("회원1", "homa@naver.com", "19971109", Male.남, "01011112222" ,"1111");

        //when
        memberService.joinMember(member);
        em.flush();
        em.clear();

        //then
        assertEquals(member.getId(),memberRepository.findMemberById(member.getId()).getId());
    }
    @Test
    public void 회원센터정보등록() throws Exception{
        //given
        Member member = new Member("회원1", "homa@naver.com", "19971109", Male.남, "01011112222" ,"1111");
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

    @Test
    public void 로그인() throws Exception{
        //given

        //when
        LoginMemberDto loginMemberDto = memberService.logIn("test3@naver.com", "ft1333");
        //then
        Assert.assertEquals(loginMemberDto.getEmail(),"test3@naver.com");
    }

    @Test
    public void 비밀번호변경() throws Exception{
        //given
        String encode = passwordEncoder.encode("1111");
        Member member = new Member("회원1", "tw4@naver.com", "19971109", Male.남, "01011112222",encode);
        em.persist(member);
        //when
        System.out.println(member.getId());
        memberService.updatePasswd(member.getId(), "1111","ft1333");
        em.flush();
        em.clear();
        //then
        Member findMember = memberRepository.findMemberById(member.getId());
        System.out.println(findMember.getEmail());
        Assert.assertEquals(true,passwordEncoder.matches("ft1333", findMember.getPasswd()));
    }
}