package com.ssafy.herehear.achievement.controller;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.EquipAchievementDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.service.AchievementService;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import jakarta.validation.Valid;
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

    @GetMapping("/list/{targetId}")
    public DataResponse<List<MemberAchievementDto>> getAchievementByTargetList(Authentication authentication, @PathVariable Long targetId) {
        log.info("[대상의 업적리스트 조회] targetId: {}, time: {}", targetId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<MemberAchievementDto> myAchievementList = achievementService.getMyAchievementList(targetId);

        return new DataResponse<>("200", "대상의 달성한 업적 조회", myAchievementList);
    }

    @GetMapping("/myachievement")
    public DataResponse<List<MemberAchievementDto>> getMyTitle(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());

        log.info("[달성한 업적 포함 전체조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<MemberAchievementDto> myAchievementList = achievementService.getMyAchievementList(memberId);

        return new DataResponse<>("200", "달성한 업적 조회", myAchievementList);
    }

    @PutMapping("/equip")
    public CommonResponse equipAchievement(@RequestBody @Valid EquipAchievementDto equipAchievementDto, Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());

        log.info("[칭호, 뱃지 장착] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        achievementService.equipAchievement(memberId, equipAchievementDto);

        return new CommonResponse("200", "칭호, 테두리 장착 완료");
    }

    @GetMapping("/{achievementId}")
    public DataResponse<AchievementDto> getEquipedAchievement(@PathVariable Long achievementId) {
        log.info("[칭호, 뱃지 조회] achievementId: {}, time: {}", achievementId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        AchievementDto equipedAchievementDto = achievementService.getAchievement(achievementId);

        return new DataResponse<>("200", "칭호, 뱃지 조회", equipedAchievementDto);
    }

}
