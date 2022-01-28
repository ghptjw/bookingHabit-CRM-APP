package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import todayHabit.todayHabitApp.repository.HoldingRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HoldingService {

    private final HoldingRepository holdingRepository;

    public void holdingMembership(Long memberId, Long holdingMembershipId, Long membershipId, LocalDateTime startDay, LocalDateTime endDay) {

    }
}
