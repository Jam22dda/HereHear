package com.ssafy.herehear.achievement.controller;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.dto.TitleCodeDto;
import com.ssafy.herehear.achievement.service.AchievementService;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev/api/achievement")
@Slf4j
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping("/list")
    public DataResponse<List<AchievementDto>> getAchievementList() {
        log.info("[업적리스트 조회] {}", TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<AchievementDto> achievementDtoList = achievementService.getAchievementList();
        return new DataResponse<>("200", "업적리스트 조회", achievementDtoList);
    }

    @GetMapping("/myachievement")
    public DataResponse<List<MemberAchievementDto>> getMyTitle() {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[달성한 업적 조회] memberId - {}, time - {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<MemberAchievementDto> myAchievementList = achievementService.getMyAchievementList(memberId);

        return new DataResponse<>("200", "달성한 업적 조회", myAchievementList);
    }

    @GetMapping("/mytitle")
    public DataResponse<List<TitleCodeDto>> getMyAchievement() {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[내 칭호 목록 조회] memberId - {}, time - {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<MemberAchievementDto> myAchievementList = achievementService.getMyAchievementList(1L);

        List<TitleCodeDto> myTitleList = myAchievementList.stream()
                .map(MemberAchievementDto::getTitle)
                .toList();

        return new DataResponse<>("200", "내 칭호 목록 조회", myTitleList);
    }

}
