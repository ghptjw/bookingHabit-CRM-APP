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

    public Optional<Integer> findMaxWaitingNumber(Long classId) {
        return Optional.ofNullable(em.createQuery("select max(w.waitingNumber) from WaitingMember w " +
                        " join w.schedule s " +
                        " where s.id = :classId ", Integer.class)
                .setParameter("classId", classId)
                .getSingleResult());
    }

    public List<WaitingMember> findByMemberIdWithClassId(Long memberId, Long classId) {
        return em.createQuery("select w from WaitingMember w " +
                        " join w.member m " +
                        " join w.schedule s " +
                        " where m.id = :memberId" +
                        " and s.id = :classId", WaitingMember.class)
                .setParameter("memberId", memberId)
                .setParameter("classId", classId)
                .getResultList();
    }
    public List<WaitingMember> findByClassId(Long classId) {
        return em.createQuery("select w from WaitingMember w " +
                        " join w.member m " +
                        " join w.schedule s " +
                        " where s.id = :classId", WaitingMember.class)
                .setParameter("classId", classId)
                .getResultList();
    }

    public void save(WaitingMember waitingMember) {
        em.persist(waitingMember);
    }

    public void deleteById(WaitingMember waitingMember) {
        em.remove(waitingMember);
    }

    public List<WaitingMember>  findByBehindWaitingMember(Long classId, int waitingNumber) {
        return em.createQuery("select w from WaitingMember w" +
                        " join w.schedule s" +
                        " where s.id = :classId " +
                        " and w.waitingNumber > :waitingNumber")
                .setParameter("classId", classId)
                .setParameter("waitingNumber", waitingNumber)
                .getResultList();
    }

}
