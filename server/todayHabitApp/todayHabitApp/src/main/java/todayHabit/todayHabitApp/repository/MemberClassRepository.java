package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.member.MemberClass;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberClassRepository {

    private final EntityManager em;

    public List<MemberClass> findByMemberOwnMembershipId(LocalDate date, Long gymId, Long membershipId) {
        return em.createQuery("select mc from MemberClass mc " +
                        " join mc.class c " +
                        " join mc.memberOwnMembership mom " +
                        " join mc.gym g " +
                        " where g.id = :gymId " +
                        " and mom.id = :membershipId" +
                        " and c.startDay = :startDay", MemberClass.class)
                .setParameter("gymId", gymId)
                .setParameter("membershipId", membershipId)
                .setParameter("startDay", date)
                .getResultList();
    }

}
