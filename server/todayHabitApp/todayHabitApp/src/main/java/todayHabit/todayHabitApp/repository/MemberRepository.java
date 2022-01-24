package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepository {
    private final EntityManager em;

    @Transactional
    public void saveMember(Member member) {
        em.persist(member);
    }

    public Member findMemberById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findMemberByEmail(String email) {
        return em.createQuery("select m from Member m " +
                        " left join fetch m.gym g " +
                        " left join fetch m.memberOwnMemberships mom " +
                        " left join fetch mom.membership ms " +
                        " where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<MemberOwnMembership> findMemberOwnMembershipByGymId(Long memberId, Long gymId) {
        return em.createQuery("select mom from MemberOwnMembership " +
                        " join mom.gym g" +
                        " join fetch mom.membership m" +
                        " where g.id = :gymId " +
                        " and m.id = :memberId")
                .setParameter("gymId", gymId)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
