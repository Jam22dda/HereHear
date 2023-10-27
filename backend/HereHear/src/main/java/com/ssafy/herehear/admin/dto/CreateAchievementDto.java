package com.ssafy.herehear.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateAchievementDto {

    private String mission;
    private String icon;
    private String titleName;
    private String badgeName;
    private String badgeImg;

    @Builder
    public CreateAchievementDto(String mission, String icon, String titleName, String borderName, String borderImg) {
        this.mission = mission;
        this.icon = icon;
        this.titleName = titleName;
        this.badgeName = borderName;
        this.badgeImg = borderImg;
    }

}
