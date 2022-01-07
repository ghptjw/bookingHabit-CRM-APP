package todayHabit.todayHabitApp.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Long id;
    private String name;
    private String phone;
}
