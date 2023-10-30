package com.ssafy.herehear.achievement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class AchievementDto {
    private Long achievementId;
    private String mission;
    private BadgeCodeDto badge;
    private TitleCodeDto title;
}
