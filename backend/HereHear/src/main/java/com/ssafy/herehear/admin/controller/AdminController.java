package com.ssafy.herehear.admin.controller;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.admin.dto.ReportDto;
import com.ssafy.herehear.admin.service.AdminService;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/achievement")
    public CommonResponse createAchievement(@RequestBody CreateAchievementDto createAchievementDto) {
        log.info("[업적 등록] createAchievementDto: {}", createAchievementDto);

        adminService.createAchievement(createAchievementDto);

        return new CommonResponse("200", "업적 등록 성공");
    }

    @GetMapping("/report")
    public DataResponse<List<ReportDto>> getReportList() {
        log.info("[신고 목록 조회] time: {}", TimeFormatUtil.formatTime(LocalDateTime.now()));

        return new DataResponse<>("200", "신고 목록 조회 성공",adminService.getReportList());
    }

}
