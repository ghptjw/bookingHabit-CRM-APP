package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.Coach;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CoachRepository {
    private final EntityManager em;

    public Coach findById(Long id) {
        return em.find(Coach.class, id);
    }
}
