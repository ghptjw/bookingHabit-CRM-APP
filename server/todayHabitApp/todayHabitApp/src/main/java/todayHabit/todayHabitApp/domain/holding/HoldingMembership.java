package todayHabit.todayHabitApp.domain.holding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "holdingMembership")
public class HoldingMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "holdingMembership_id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GYM_id")
    private Gym gym;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_membership_id")
    private MemberOwnMembership memberOwnMembership;

    @JsonIgnore
    @OneToMany(mappedBy = "holdingMembership")
    private List<HoldingList> holdingList;


    private int holdingPeriod;
    private int usingPeriod;
    private boolean deleteValue;

    public void changeDeleteValue() {
        this.deleteValue = true;
    }

    public void setUsingPeriod(int usingPeriod) {
        this.usingPeriod = usingPeriod;
    }


    public boolean getDeleteValue() {
        return this.deleteValue;
    }
}
