package todayHabit.todayHabitApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todayHabit.todayHabitApp.domain.member.MemberClass;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembership;
import todayHabit.todayHabitApp.domain.member.MemberOwnMembershipClassType;
import todayHabit.todayHabitApp.dto.schedule.DayClassDto;
import todayHabit.todayHabitApp.repository.ClassRepository;
import todayHabit.todayHabitApp.repository.MemberClassRepository;
import todayHabit.todayHabitApp.repository.MemberOwnMembershipRepository;

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
    private final MemberClassRepository memberClassRepository;

    public List<DayClassDto> DayClass(LocalDate date, Long gymId, Long membershipId) {
        List<Long> classTypeList = new ArrayList();
        MemberOwnMembership membership = memberOwnMembershipRepository.findById(membershipId);
        List<MemberOwnMembershipClassType> membershipClassTypes = membership.getMembershipClassTypes();
        for (MemberOwnMembershipClassType membershipClassType : membershipClassTypes) {
            classTypeList.add(membershipClassType.getClassType().getId());
        }
        System.out.println("classTypeList = " + classTypeList);

        return classRepository.findClassByDate(date, gymId, classTypeList).stream()
                .map(classList -> new DayClassDto(classList))
                .collect(Collectors.toList());
    }

    public List<DayClassDto> BeforeDayClass(LocalDate now, Long gymId, Long membershipId) {
        List<MemberClass> classLists = memberClassRepository.findByMemberOwnMembershipId(now, gymId, membershipId);
        return classLists.stream().map(classList -> new DayClassDto(classList))
                .collect(Collectors.toList());
    }

}

