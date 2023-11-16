package com.ssafy.herehear.admin.dto;

import com.ssafy.herehear.entity.type.AchievementCategoryType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateAchievementDto {

    @NotEmpty(message = "mission 값은 필수값입니다.")
    @Size(min = 0, max = 1000, message = "최대 1000자 까지 입력이 가능합니다.")
    private String mission;

    @NotEmpty(message = "칭호 이름은 필수값 입니다.")
    @Size(max = 50, message = "최대 50자까지 입력이 가능합니다.")
    private String titleName;

    @NotEmpty(message = "뱃지 이름은 필수값 입니다.")
    @Size(max = 50, message = "최대 50글자까지 입력이 가능합니다.")
    private String badgeName;

    @Size(max = 255, message = "최대 255자까지 입력이 가능합니다.")
    private String badgeImg;

    private int count;

    @Valid
    @NotNull(message = "업적의 타입은 필수값 입니다")
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
