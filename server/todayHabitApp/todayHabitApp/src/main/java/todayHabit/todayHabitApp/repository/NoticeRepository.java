package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.Notice;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepository {

    private final EntityManager em;

    public List<Notice> findByGymId(Long gymId) {
        return em.createQuery("select n from Notice n " +
                " join n.gym g " +
                " where g.id = :gymId")
                .setParameter("gymId", gymId)
                .getResultList();
    }

    public Notice findById(Long noticeId) {
        return em.find(Notice.class, noticeId);
    }
}
