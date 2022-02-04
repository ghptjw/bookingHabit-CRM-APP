package todayHabit.todayHabitApp.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import todayHabit.todayHabitApp.service.HoldingService;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class HoldingController {

    private final HoldingService holdingService;

    @PostMapping("/hold/membership/check")
    public int checkHoldingMembership(@RequestBody HoldingDto request) throws Exception {
        int classListCount = holdingService.checkHoldingMembership(request.getGymId(),
                request.getMembershipId(), request.getStartDay(), request.getEndDay());
        return classListCount;
    }

    @PostMapping("/hold/membership")
    public String holdingMembership(@RequestBody HoldingDto request) throws Exception {
        return holdingService.holdingMembership(request.getMembershipId(),
                request.getHoldingMembershipId(), request.getStartDay(), request.getEndDay());
    }

    @PostMapping("/hold/membership/cancel")
    public String cancelHoldingMembership(@RequestBody HoldingDto request) throws Exception {
        return holdingService.holdingMembership(request.getMembershipId(),
                request.getHoldingMembershipId(), request.getStartDay(), request.getEndDay());
    }
    @Data
    static class HoldingDto {
        @NotNull
        private Long gymId;
        @NotNull
        private Long memberId;
        @NotNull
        private Long membershipId;
        @NotNull
        private Long holdingMembershipId;
        @NotNull
        private LocalDate startDay;
        @NotNull
        private LocalDate endDay;
    }
}
