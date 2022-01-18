package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.Member;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembershipClassType;
import todayHabit.todayHabitApp.dto.DayClassDto;
import todayHabit.todayHabitApp.repository.ClassRepository;
import todayHabit.todayHabitApp.repository.MemberOwnMembershipRepository;
import todayHabit.todayHabitApp.repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassService {
    private final ClassRepository classRepository;
    private final MemberOwnMembershipRepository memberOwnMembershipRepository;

    public List<DayClassDto> DayClass(LocalDate date, Long gymId, Long memberId) {
        List<Long> classTypeList = new ArrayList();
        List<MemberOwnMembership> memberOwnMembershipFindByMemberList
                = memberOwnMembershipRepository.findMemberOwnMembershipFindByMemberId(memberId);
        for (MemberOwnMembership memberOwnMembership : memberOwnMembershipFindByMemberList) {
            memberOwnMembership.getMembershipClassTypes().stream()
                    .map(MemberOwnMembershipClassType::getId)
                    .forEach(classTypeList::add);
        }
        return classRepository.findClassByDate(date, gymId, classTypeList).stream()
                .map(classList -> new DayClassDto(classList))
                .collect(Collectors.toList());
    }
}

