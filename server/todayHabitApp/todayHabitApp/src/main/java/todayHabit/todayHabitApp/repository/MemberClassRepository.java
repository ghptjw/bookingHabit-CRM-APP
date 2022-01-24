package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.member.MemberClass;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
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

    public void save(MemberClass memberClass) {
        em.persist(memberClass);
    }

    public List<MemberClass> findByMemberIdWithClassId(Long memberId, Long classId) {
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



}
