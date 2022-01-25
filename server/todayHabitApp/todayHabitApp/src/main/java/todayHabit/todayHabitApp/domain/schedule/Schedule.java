package todayHabit.todayHabitApp.domain.schedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import todayHabit.todayHabitApp.domain.classType.ClassType;
import todayHabit.todayHabitApp.domain.gym.Gym;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Table(name = "class")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GYM_id")
    private Gym gym;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classType_id")
    private ClassType classType;

    @Column(name = "classType_name")
    private String classTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "classes")
    private List<CoachClass> coachClasses;


    private int category;
    private LocalDate startDay;
    private LocalTime startTime;
    private int period;
    private int totalReservation;
    private int reserveNumber;
    private int decrease;
    private String repeatDay;
    private LocalDate repeatEndDay;
    private String cycle;


    public void increaseCount() {
        this.reserveNumber += 1;
    }

    public void decreaseCount() {
        this.reserveNumber -= 1;
    }
}
