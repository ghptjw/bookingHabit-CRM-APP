package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.ReserveBlock;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReserveBlockRepository {
    private final EntityManager em;

    public List<ReserveBlock> findByStartDay(LocalDate date) {
        return em.createQuery("select r from ReserveBlock r " +
                        " where r.startDay = :startDay")
                .setParameter("startDay", date)
                .getResultList();
    }
}
