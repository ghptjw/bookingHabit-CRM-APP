package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.holding.HoldingList;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HoldingListRepository {

    private final EntityManager em;

    @Transactional
    public void save(HoldingList holdingList) {
        em.persist(holdingList);
    }

    public HoldingList findByHoldingMembershipId(long id) {
        return em.createQuery("select hl from HoldingList hl " +
                        " join hl.holdingMembership h" +
                        " where h.id = :id ",HoldingList.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
