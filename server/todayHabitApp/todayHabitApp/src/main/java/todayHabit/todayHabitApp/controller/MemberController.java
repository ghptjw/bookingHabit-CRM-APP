package todayHabit.todayHabitApp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import todayHabit.todayHabitApp.domain.member.Male;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.dto.member.CreateMemberDto;
import todayHabit.todayHabitApp.dto.member.LoginMemberDto;
import todayHabit.todayHabitApp.service.MemberService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/member/save")
    public String saveMember(@RequestBody @Valid CreateMemberDto request) {
        Male male = Male.RequestToEnum(request.getMale());
        String encodingPasswd = passwordEncoder.encode(request.getPasswd());
        Member member = new Member(
                request.getName(), request.getEmail(),
                request.getBirth(), male, request.getPhone(), encodingPasswd);
        memberService.joinMember(member);
        return "생성이 완료되었습니다.";
    }

    @PutMapping("/member/update/gym/{id}")
    public String updateMemberGymCode(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberGymInfo request) {
        memberService.updateCenterInfo(request.getSerialNumber(), id);
        return "수정이 완료되었습니다.";
    }

    @PostMapping("/member/login")
    public Optional<LoginMemberDto> loginMember(@RequestBody LoginInfo request) throws Exception {
        Optional<LoginMemberDto> loginMemberDto = Optional.ofNullable(memberService.logIn(request.email, request.passwd));
        return loginMemberDto;
    }

    @PutMapping("/member/update/passwd/{id}")
    public String updateMemberPasswd(@PathVariable("id") Long id, @RequestBody @Valid UpdateMemberPasswd request) throws Exception {
        memberService.updatePasswd(id, request.getBeforePasswd(), request.getNewPasswd());
        return "수정이 완료되었습니다.";
    }

    /*
     * data
     * */
    @Data
    static class UpdateMemberPasswd {
        @NotEmpty(message = "기존 비밀번호를 입력해주세요.")
        private String beforePasswd;
        @NotEmpty(message = "새로운 비밀번호를 입력해주세요.")
        private String newPasswd;
    }


    @Data
    static class UpdateMemberGymInfo {
        @NotEmpty(message = "센터 번호를 입력해주세요.")
        private String serialNumber;
    }

    @Data
    static class LoginInfo {
        @NotEmpty(message = "이메일을 입력해주세요.")
        private String email;
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        private String passwd;

    }
}
