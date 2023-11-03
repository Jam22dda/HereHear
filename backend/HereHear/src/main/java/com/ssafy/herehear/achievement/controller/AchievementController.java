package com.ssafy.herehear.achievement.controller;

import com.ssafy.herehear.achievement.dto.*;
import com.ssafy.herehear.achievement.service.AchievementService;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievement")
@Slf4j
public class AchievementController {

    private final AchievementService achievementService;

    @GetMapping("/list")
    public DataResponse<List<AchievementDto>> getAchievementList() {
        log.info("[업적리스트 조회] time: {}", TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<AchievementDto> achievementDtoList = achievementService.getAchievementList();
        return new DataResponse<>("200", "업적리스트 조회", achievementDtoList);
    }

    @GetMapping("/myachievement")
    public DataResponse<List<MemberAchievementDto>> getMyTitle(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());

        log.info("[달성한 업적 포함 전체조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<MemberAchievementDto> myAchievementList = achievementService.getMyAchievementList(memberId);

        return new DataResponse<>("200", "달성한 업적 조회", myAchievementList);
    }

    @GetMapping("/{memberId}")
    public DataResponse<Integer> getAchievement(@PathVariable Long memberId) {
        log.info("[달성한 업적 개수 조회] 조회 memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        int count = achievementService.getAchievementCount(memberId);

        return new DataResponse<>("200", "달성한 업적 개수 조회", count);
    }

    @PutMapping("/equip")
    public CommonResponse equipAchievement(@RequestBody EquipAchievementDto equipAchievementDto, Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());

        log.info("[칭호, 뱃지 장착] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        achievementService.equipAchievement(memberId, equipAchievementDto);

        return new CommonResponse("200", "칭호, 테두리 장착 완료");
    }

    @GetMapping("/equiped")
    public DataResponse<AchievementDto> getEquipedAchievement(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());

        log.info("[장착한 칭호, 뱃지 조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        AchievementDto equipedAchievementDto = achievementService.getAchievement(memberId);

        return new DataResponse<>("200", "장착한 칭호, 뱃지 조회", equipedAchievementDto);
    }

}
