package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.domain.gym.GymContainMember;
import todayHabit.todayHabitApp.domain.member.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GymRepository {
    private final EntityManager em;

    public Gym findById(Long id) {
        return em.find(Gym.class, id);
    }

    public Optional<Gym> findGymBySerialNumber(String serialNumber) {
        System.out.println("serialNumber = " + serialNumber);
        return Optional.ofNullable(em.createQuery("select g from Gym g where g.serialNumber = :serialNumber", Gym.class)
                .setParameter("serialNumber", serialNumber)
                .getSingleResult());
    }

    public void insertGymContainMember(Gym gym, Member member) {
        GymContainMember gymContainMember = new GymContainMember(gym, member);
        em.persist(gymContainMember);
    }

    public List<GymContainMember> findGymContainMemberByGymIdWithMemberId(Long gymId, Long memberId) {
        List<GymContainMember> resultList = em.createQuery("select gcm from GymContainMember gcm " +
                        " join gcm.gym g" +
                        " join gcm.member m" +
                        " where g.id = :gymId " +
                        " and m.id = :memberId", GymContainMember.class)
                .setParameter("gymId", gymId)
                .setParameter("memberId", memberId)
                .getResultList();
        return resultList;

    }
}
