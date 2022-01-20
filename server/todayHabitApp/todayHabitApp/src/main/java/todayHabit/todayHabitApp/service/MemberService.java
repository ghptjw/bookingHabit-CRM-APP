package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.gym.GymContainMember;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.firebase.FirebaseRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final FirebaseRepository firebaseRepository;

    @Transactional
    public Long joinMember(Member member) {
        try{
            validateDuplicateMember(member);
            memberRepository.saveMember(member);
            firebaseRepository.initialize();
            firebaseRepository.saveMember(member);
        }catch(Exception e){
            e.printStackTrace();
        }
        return member.getId();
    }

    @Transactional
    public String updateCenterInfo(String serialNumber, Long memberId) {
        firebaseRepository.initialize();
        Optional<Gym> findGym = gymRepository.findGymBySerialNumber(serialNumber);
        if(findGym.isEmpty()){
            throw new IllegalStateException("존재하지 않는 센터입니다.");
        }
        Member findMember = memberRepository.findMemberById(memberId);
        Gym gym = findGym.get();
        List<GymContainMember> gymContainMemberList
                = gymRepository.findGymContainMemberByGymIdWithMemberId(gym.getId(), findMember.getId());
        if (findMember.getPasswd() == null) { // 신규 센터 등록일 때 -- 비밀번호 설정 및 센터 등록
            String encodingPasswd = passwordEncoder.encode(serialNumber);
            findMember.updatePasswd(encodingPasswd);
            findMember.updateGymInfo(gym);
            firebaseRepository.updateMemberGymId(findMember,encodingPasswd);
        }
        if(gymContainMemberList.isEmpty()) { // 등록된 센터가 아닐 때 -- 센터 등록
            gymRepository.insertGymContainMember(gym, findMember);
        }else{
            throw new IllegalStateException("이미 등록된 센터입니다.");
        }
        return "등록이 완료되었습니다.";
    }

    public Member logIn(String email, String passwd) {
        List<Member> findMember = memberRepository.findMemberByEmail(email);
        if(findMember.isEmpty()){
            throw new IllegalStateException("존재 하지 않는 회원입니다");
        }
        if(passwordEncoder.matches(passwd,findMember.get(0).getPasswd())){
            return findMember.get(0);
        }else{
            throw new IllegalStateException("패스워드가 틀렸습니다");
        }
    }

    @Transactional
    public void updatePasswd(Long memberId, String beforePasswd, String newPasswd) throws Exception{
        firebaseRepository.initialize();
        Member findMember = memberRepository.findMemberById(memberId);
        if (!passwordEncoder.matches(beforePasswd, findMember.getPasswd())) {
            throw new IllegalStateException("패스워드가 틀렸습니다");
        }
        String encodingPasswd = passwordEncoder.encode(newPasswd);
        findMember.updatePasswd(encodingPasswd);
        firebaseRepository.updateMemberGymId(findMember,encodingPasswd);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findMemberByEmail(member.getEmail());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
