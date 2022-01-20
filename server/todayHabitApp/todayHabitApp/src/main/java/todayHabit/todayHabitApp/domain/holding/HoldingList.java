package todayHabit.todayHabitApp.domain.holding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class HoldingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holdingMembership_id")
    private HoldingMembership holdingMembership;

    private LocalDate startDay;
    private LocalDate endDay;
    private String description;

    @Enumerated(EnumType.STRING)
    private HoldingLocation holdingLocation;

}
