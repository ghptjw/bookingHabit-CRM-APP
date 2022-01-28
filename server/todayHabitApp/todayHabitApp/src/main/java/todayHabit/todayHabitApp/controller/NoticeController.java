package todayHabit.todayHabitApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import todayHabit.todayHabitApp.dto.NoticeDto;
import todayHabit.todayHabitApp.service.NoticeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/{gymId}")
    public List<NoticeDto> getNoticeList(@PathVariable("gymId") Long gymId) {
        return noticeService.selectAllNotice(gymId);
    }

    @GetMapping("/notice/info/{noticeId}")
    public NoticeDto getNoticeInfo(@PathVariable("noticeId") Long noticeId) {
        return noticeService.selectNotice(noticeId);
    }
}
