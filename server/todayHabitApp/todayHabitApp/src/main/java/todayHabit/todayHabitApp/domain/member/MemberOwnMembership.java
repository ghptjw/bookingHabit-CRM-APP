package todayHabit.todayHabitApp.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.domain.holding.HoldingMembership;
import todayHabit.todayHabitApp.domain.membership.Membership;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "member_membership")
public class MemberOwnMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GYM_id")
    @JsonIgnore
    private Gym gym;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id",insertable = false,updatable = false)
    @JsonIgnore
    private Membership membership;

    @JsonIgnore
    @OneToMany(mappedBy = "memberOwnMembership")
    private List<MemberOwnMembershipClassType> membershipClassTypes;

    @JsonIgnore
    @OneToMany(mappedBy = "memberOwnMembership")
    private List<HoldingMembership> holdingMemberships;

    private LocalDate startDay;
    private LocalDate endDay;

    private int countClass;
    private int payment;
    private LocalDate paymentDay;
    private int cash;
    private int card;
    private int accountReceivable;
    private int DayAttend;
    private int weekAttend;
    private LocalDateTime registerDate;
    private int maxCountClass;

    public boolean getAvailable() {
        int compare = this.endDay.compareTo(LocalDate.now());
        if (compare < 0 || (this.maxCountClass <= this.countClass)) {
            return false;
        }else{
            return true;
        }
    }

    public boolean increaseMembership(int count) {
        if (this.countClass + count > this.maxCountClass) {
            return false;
        }
        this.countClass += count;
        return true;
    }

    public void decreaseMembership(int count) {
        this.countClass -= count;
    }

    public LocalDate getEndDay() {
        return this.startDay.plusDays(this.membership.getPeriod());
    }
}

