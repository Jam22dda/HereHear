package com.ssafy.herehear.admin.controller;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.admin.service.AdminService;
import com.ssafy.herehear.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/achievement")
    public CommonResponse createAchievement(@RequestBody CreateAchievementDto createAchievementDto) {
        log.info("[업적 등록] createAchievementDto: {}", createAchievementDto);

        adminService.createAchievement(createAchievementDto);

        return new CommonResponse("200", "업적 등록 성공");
    }

}
