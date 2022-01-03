package todayHabit.todayHabitApp.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import todayHabit.todayHabitApp.domain.Male;
import todayHabit.todayHabitApp.domain.Member;
import todayHabit.todayHabitApp.dto.member.CreateMemberDto;
import todayHabit.todayHabitApp.service.MemberService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/save")
    public String saveMember(@RequestBody @Valid CreateMemberDto request) {
        Male male = Male.RequestToEnum(request.getMale());
        Member member = new Member(
                request.getName(),
                request.getEmail(),
                request.getBirth(),
                male,
                request.getPhone()
        );
        memberService.joinMember(member);
        return "생성이 완료되었습니다.";
    }

    @PutMapping("/member/update/gym/{id}")
    public String updateMemberGymCode(@PathVariable("id") Long id, @RequestBody UpdateMemberGymInfo request) {
        memberService.updateCenterInfo(request.getSerialNumber(), id);
        return "수정이 완료되었습니다.";
    }
    //data
    @Data
    static class UpdateMemberGymInfo {
        private String serialNumber;
    }
}
