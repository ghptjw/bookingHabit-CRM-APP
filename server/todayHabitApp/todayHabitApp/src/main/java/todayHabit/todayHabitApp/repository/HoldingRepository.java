package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.holding.HoldingMembership;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class HoldingRepository {

    private final EntityManager em;

    public HoldingMembership findById(Long holdingMembershipId) {
        return em.find(HoldingMembership.class, holdingMembershipId);
    }
}
