package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.gym.GymContainMember;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.gym.Gym;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.dto.member.LoginMemberDto;
import todayHabit.todayHabitApp.dto.member.MemberOwnMembershipsDto;
import todayHabit.todayHabitApp.error.*;
import todayHabit.todayHabitApp.firebase.FirebaseRepository;
import todayHabit.todayHabitApp.repository.GymContainMemberRepository;
import todayHabit.todayHabitApp.repository.GymRepository;
import todayHabit.todayHabitApp.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final GymRepository gymRepository;
    private final GymContainMemberRepository gymContainMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseRepository firebaseRepository;

    @Transactional
    public Long joinMember(Member member) throws Exception{
        validateDuplicateMember(member);
        memberRepository.saveMember(member);
        firebaseRepository.initialize();
        firebaseRepository.saveMember(member);
        return member.getId();
    }

    @Transactional
    public String updateCenterInfo(String serialNumber, Long memberId) throws Exception {
        firebaseRepository.initialize();
        Optional<Gym> findGym = gymRepository.findGymBySerialNumber(serialNumber);
        if(findGym.isEmpty()){
            throw new NonExistGymException();
        }
        Member findMember = memberRepository.findMemberById(memberId);
        Gym gym = findGym.get();
        List<GymContainMember> gymContainMemberList
                = gymRepository.findGymContainMemberByGymIdWithMemberId(gym.getId(), findMember.getId());
        GymContainMember gymContainMember = new GymContainMember(gym, findMember);
        if (findMember.getGym() == null) { // 센터 정보가 없을 때 -- 회원정보에 센터 정보 추가
            gymContainMember.BookmarkGym();
            findMember.updateGymInfo(gym);
        }
        if(gymContainMemberList.isEmpty()) { // 등록된 센터가 아닐 때 -- 센터 등록
            gymRepository.insertGymContainMember(gymContainMember);
        }else{
            throw new AlreadyRegisterException();
        }
        return "등록이 완료되었습니다.";
    }

    public LoginMemberDto logIn(String email, String passwd) throws Exception{
        List<Member> findMember = memberRepository.findMemberByEmail(email);
        if(findMember.isEmpty()){
            throw new NotExistMemberException();
        }
        if(passwordEncoder.matches(passwd,findMember.get(0).getPasswd())){
            return new LoginMemberDto(findMember.get(0));
        }else{
            throw new NotCorrectPasswdException();
        }
    }

    @Transactional
    public void updatePasswd(Long memberId, String beforePasswd, String newPasswd) throws Exception{
        firebaseRepository.initialize();
        Member findMember = memberRepository.findMemberById(memberId);
        if (!passwordEncoder.matches(beforePasswd, findMember.getPasswd())) {
            throw new NotCorrectPasswdException();
        }
        String encodingPasswd = passwordEncoder.encode(newPasswd);
        findMember.updatePasswd(encodingPasswd);
        firebaseRepository.updateMemberGymId(findMember,encodingPasswd);
    }

    public List<MemberOwnMembershipsDto> changeMemberOwnMembership(Long memberId, Long gymId) {
        List<MemberOwnMembership> membershipList = memberRepository.findMemberOwnMembershipByGymId(memberId, gymId);
        return membershipList.stream()
                .map(membership -> new MemberOwnMembershipsDto(membership))
                .collect(Collectors.toList());
    }

    @Transactional
    public void bookmarkGym(Long oldGymId, Long newGymId,Long memberId) {
        Optional<GymContainMember> oldGym = gymContainMemberRepository.findByGymIdWithMemberId(memberId, oldGymId);
        Optional<GymContainMember> newGym = gymContainMemberRepository.findByGymIdWithMemberId(memberId, newGymId);
        oldGym.get().UnBookmarkGym();
        newGym.get().BookmarkGym();
    }

    private void validateDuplicateMember(Member member) throws Exception{
        List<Member> findMember = memberRepository.findMemberByEmail(member.getEmail());
        System.out.println("findMember.size() = " + findMember.size());
        if (!findMember.isEmpty()) {
            throw new AlreadyExistMemberException();
        }
    }
}
