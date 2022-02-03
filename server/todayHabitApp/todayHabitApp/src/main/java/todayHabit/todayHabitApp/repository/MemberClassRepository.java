package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.MemberClass;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberClassRepository {

    private final EntityManager em;

    public List<MemberClass> findByMemberOwnMembershipId(LocalDate date, Long gymId, Long membershipId) {
        return em.createQuery("select mc from MemberClass mc " +
                        " join fetch mc.schedule c " +
                        " join mc.memberOwnMembership mom " +
                        " join mc.gym g " +
                        " where g.id = :gymId " +
                        " and mom.id = :membershipId" +
                        " and c.startDay = :startDay " +
                        " and c.startTime >= :startTime", MemberClass.class)
                .setParameter("gymId", gymId)
                .setParameter("membershipId", membershipId)
                .setParameter("startDay", date)
                .setParameter("startTime", LocalTime.now())
                .getResultList();
    }

    @Transactional
    public void save(MemberClass memberClass) {
        em.persist(memberClass);
    }

    public List<MemberClass> findByMemberIdWithClassIdByDate(Long memberId, Long classId) {
        return em.createQuery("select mc from MemberClass mc " +
                        " join mc.member m " +
                        " join mc.gym g " +
                        " join mc.schedule s " +
                        " where m.id = :memberId" +
                        " and s.id = :classId")
                .setParameter("memberId", memberId)
                .setParameter("classId", classId)
                .getResultList();
    }

    @Transactional
    public void deleteById(MemberClass memberClass) {
        em.remove(memberClass);
    }

    public List<MemberClass> findByMemberIdWithClassId(Long membershipId,Long classId) {
        return em.createQuery("select mc from MemberClass mc " +
                        " join mc.memberOwnMembership mom " +
                        " join mc.schedule s " +
                        " where mom.id = :membershipId" +
                        " and s.id = :classId", MemberClass.class)
                .setParameter("membershipId", membershipId)
                .setParameter("classId", classId)
                .getResultList();
    }

    public List<MemberClass> findBetweenDate(Long gymId, LocalDate startDay, LocalDate endDay, Long membershipId) {
        return em.createQuery("select mc from MemberClass mc " +
                        " join fetch mc.schedule s " +
                        " join mc.memberOwnMembership mom " +
                        " where mom.id = :membershipId " +
                        " and s.startDay between :startDay and :endDay ", MemberClass.class)
                .setParameter("membershipId", membershipId)
                .setParameter("startDay", startDay)
                .setParameter("endDay", endDay)
                .getResultList();
    }

}
