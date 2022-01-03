package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.gym.Gym;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GymRepository {
    private final EntityManager em;

    public Optional<Gym> findGymBySerialNumber(String serialNumber) {
        return Optional.ofNullable(em.createQuery("select g from Gym g where serialNumber = :serialNumber", Gym.class)
                .setParameter("serialNumber", serialNumber)
                .getSingleResult());
    }
}
