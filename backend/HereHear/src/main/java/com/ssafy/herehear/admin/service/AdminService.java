package com.ssafy.herehear.admin.service;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.admin.dto.ReportDto;

import java.util.List;

public interface AdminService {
    void createAchievement(CreateAchievementDto createAchievementDto);

    List<ReportDto> getReportList();
}
