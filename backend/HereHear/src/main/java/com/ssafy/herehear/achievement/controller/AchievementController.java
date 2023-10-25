package com.ssafy.herehear.achievement.controller;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.mapper.AchievementMapper;
import com.ssafy.herehear.achievement.service.AchievementService;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.global.response.DataResponse;
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
        log.info("[업적리스트 조회] {}", LocalDateTime.now());

        List<AchievementDto> achievementDtoList = achievementService.getAchievementList();
        return new DataResponse<>("200", "업적리스트 조회", achievementDtoList);
    }
}
