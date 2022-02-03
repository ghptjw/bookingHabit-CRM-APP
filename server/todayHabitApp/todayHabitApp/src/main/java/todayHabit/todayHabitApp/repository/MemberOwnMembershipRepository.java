package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberOwnMembershipRepository {
    private final EntityManager em;

    public List<MemberOwnMembership> findMemberOwnMembershipFindByMemberId(Long memberId, Long membershipId) {
        return em.createQuery("select mom from MemberOwnMembership mom " +
                        " join mom.member m" +
                        " join mom.membership ms" +
                        " where m.id = :memberId" +
                        " and ms.id = :membershipId")
                .setParameter("memberId", memberId)
                .setParameter("membershipId", membershipId)
                .getResultList();
    }

    public MemberOwnMembership findById(Long membershipId) {
        return em.find(MemberOwnMembership.class, membershipId);
    }

    public MemberOwnMembership findByIdToHolding(Long membershipId) {
        return em.createQuery("select mom from MemberOwnMembership mom " +
                        " join fetch mom.membership ms" +
                        " where mom.id = :membershipId", MemberOwnMembership.class)
                .setParameter("membershipId", membershipId)
                .getSingleResult();
    }
}

