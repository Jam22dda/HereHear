package com.ssafy.herehear.member.dto.response;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowerResDto {

    private Long memberId;
    private String nickname;
    private ProfileCharacterResDto profileCharacter;
    private AchievementDto achievement;
    private Boolean isFollowed;

}
