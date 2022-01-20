package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.gym.GymContainMember;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GymContainMemberRepository {
    private final EntityManager em;

    public List<GymContainMember> findByMemberId(Long memberId) {
        return em.createQuery("select gcm from GymContainMember gcm " +
                        " join gcm.member m " +
                        " where m.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
