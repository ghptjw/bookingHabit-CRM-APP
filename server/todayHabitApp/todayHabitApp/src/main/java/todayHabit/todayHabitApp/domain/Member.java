package todayHabit.todayHabitApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import todayHabit.todayHabitApp.domain.gym.Gym;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Member {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GYM_id")
    @JsonIgnore
    private Gym gym;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String image;
    private String email;
    private String passwd;
    private String birth;

    @Enumerated(EnumType.STRING)
    private Male male;

    private String phone;
    private boolean expire;
    private boolean approve;
    private int point;
    private int countClass;
    private String description;
    private LocalDateTime registerDate;

    @Override
    public String toString() {
        return "Member{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", logoImage='" + image + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", birth='" + birth + '\'' +
                ", male=" + male +
                ", phone='" + phone + '\'' +
                ", expire=" + expire +
                ", approve=" + approve +
                ", point=" + point +
                ", countClass=" + countClass +
                ", description='" + description + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }

    protected Member() {

    }

    public Member(String name, String email, String birth, Male male, String phone) {
        this.name = name;
        this.email = email;
        this.birth = birth;
        this.male = male;
        this.phone = phone;
        this.approve = true;
    }

    // 센터 등록
    public void updateGymInfo(Gym gym) {
        this.setGym(gym);
    }
}
