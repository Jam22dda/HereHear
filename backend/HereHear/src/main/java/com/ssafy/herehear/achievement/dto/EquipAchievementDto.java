package com.ssafy.herehear.achievement.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipAchievementDto {

    @NotNull(message = "업적 id 값은 필수값 입니다")
    private Long achievementId;

}
