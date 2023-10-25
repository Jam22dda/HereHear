package com.ssafy.herehear.achievement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAchievementDto {

    private Long userId;
    private Long achievementId;
    private String mission;
    private String icon;

    private BorderCodeDto border;
    private TitleCodeDto title;
    private String clearTime;

}
