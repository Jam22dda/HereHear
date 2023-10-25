package com.ssafy.herehear.achievement.dto;

import com.ssafy.herehear.entity.BorderCode;
import com.ssafy.herehear.entity.TitleCode;
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

    private BorderCodeDto border;
    private TitleCodeDto title;
}
