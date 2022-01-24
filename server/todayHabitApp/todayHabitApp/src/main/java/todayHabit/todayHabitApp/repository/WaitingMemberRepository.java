package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.WaitingMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WaitingMemberRepository {

    public final EntityManager em;

    public Optional<WaitingMember> findMaxWaitingNumber(Long classId) {
        return Optional.ofNullable(em.createQuery("select max(w.waitingNumber) from WaitingMember w " +
                        " join w.schedule s " +
                        " where s.id = :classId ", WaitingMember.class)
                .setParameter("classId", classId)
                .getSingleResult());
    }

    public List<WaitingMember> findByMemberIdWithClassId(Long memberId, Long classId) {
        return em.createQuery("select w from WaitingMember w " +
                        " join w.member m " +
                        " join w.schedule s " +
                        " where m.id = :memberId" +
                        " and s.id = :classId")
                .setParameter("memberId", memberId)
                .setParameter("classId", classId)
                .getResultList();
    }
}
