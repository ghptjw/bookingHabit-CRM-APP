package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.Member;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.repository.GymRepository;
import todayHabit.todayHabitApp.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final GymRepository gymRepository;
    @Transactional
    public Long joinMember(Member member) {
        validateDuplicateMember(member);
        memberRepository.saveMember(member);
        return member.getId();
    }

    @Transactional
    public String updateCenterInfo(String serialNumber, Long memberId) {
        Optional<Gym> findGym = gymRepository.findGymBySerialNumber(serialNumber);
        if(findGym.isEmpty()){
            throw new IllegalStateException("존재하지 않는 센터입니다.");
        }
        Member findMember = memberRepository.findMemberById(memberId);
        System.out.println("findGym = " + findGym.get().getId());
        Gym gym = findGym.get();
        findMember.setGym(gym);

        System.out.println("findMember.getName() = " + findMember.getEmail());
        return "등록이 완료되었습니다.";
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findMemberByEmail(member.getEmail());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
