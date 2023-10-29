package com.ssafy.herehear.admin.dto;

import com.ssafy.herehear.entity.type.AchievementCategoryType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateAchievementDto {

    private String mission;
    private String titleName;
    private String badgeName;
    private String badgeImg;
    private int count;
    private AchievementCategoryType category;

    @Builder
    public CreateAchievementDto(String mission, String titleName, String badgeName, String badgeImg, int count, AchievementCategoryType category) {
        this.mission = mission;
        this.titleName = titleName;
        this.badgeName = badgeName;
        this.badgeImg = badgeImg;
        this.count = count;
        this.category = category;
    }
}
