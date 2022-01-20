package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberOwnMembershipRepository {
    private final EntityManager em;

    public List<MemberOwnMembership> findMemberOwnMembershipFindByMemberId(Long memberId) {
        return em.createQuery("select mom from MemberOwnMembership mom " +
                        " join mom.member m" +
                        " where m.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }
}

