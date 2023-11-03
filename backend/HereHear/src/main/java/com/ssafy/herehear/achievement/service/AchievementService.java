package com.ssafy.herehear.achievement.service;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.EquipAchievementDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;

import java.util.List;

public interface AchievementService {
    List<AchievementDto> getAchievementList();

    List<MemberAchievementDto> getMyAchievementList(Long userId);

    int getAchievementCount(Long memberId);

    void equipAchievement(Long memberId, EquipAchievementDto equipAchievementDto);

    AchievementDto getAchievement(Long memberId);
}
