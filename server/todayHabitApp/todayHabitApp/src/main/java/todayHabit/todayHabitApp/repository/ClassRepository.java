package todayHabit.todayHabitApp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.schedule.Schedule;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassRepository {
    private final EntityManager em;

    public List<Schedule> findClassByDate(LocalDate date, Long gymId, List<Long> classTypeList) {
        String query = "select s from Schedule s" +
                " join fetch s.classType ct " +
                " join s.gym g" +
                " join fetch s.coachClasses cc" +
                " join fetch cc.coach co" +
                " where s.startDay = :startDay " +
                " and g.id = :gymId";
        if (classTypeList.size() != 0) {
            query += " and ct.id in (";
        }
        for (Long classType : classTypeList) {
            query += classType;
        }
        if (classTypeList.size() != 0) {
            query += " )";
        }
        List<Schedule> classList = em.createQuery(query, Schedule.class)
                .setParameter("startDay", date)
                .setParameter("gymId", gymId)
                .getResultList();
        return classList;
    }
}
