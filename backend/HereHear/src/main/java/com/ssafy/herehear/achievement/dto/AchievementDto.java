package com.ssafy.herehear.achievement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AchievementDto {
    private Long achievementId;
    private String mission;
    private String icon;

    private BadgeCodeDto badge;
    private TitleCodeDto title;
}
