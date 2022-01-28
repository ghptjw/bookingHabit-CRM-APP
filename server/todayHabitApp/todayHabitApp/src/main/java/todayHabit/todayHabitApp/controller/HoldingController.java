package todayHabit.todayHabitApp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todayHabit.todayHabitApp.service.HoldingService;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class HoldingController {

    private final HoldingService holdingService;

    @PostMapping("member/holdingMembership")
    public String holdingMembership(@RequestBody HoldingDto request) throws Exception {
        holdingService.holdingMembership(request.getMemberId(), request.getHoldingMembershipId(),
                request.getMembershipId(), request.getStartDay(), request.getEndDay());
        return "홀딩이 완료되었습니다";
    }

    @Data
    private class HoldingDto {
        @NotNull
        private Long memberId;
        @NotNull
        private Long membershipId;
        @NotNull
        private Long holdingMembershipId;
        @NotNull
        private LocalDateTime startDay;
        @NotNull
        private LocalDateTime endDay;
    }
}
