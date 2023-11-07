package com.ssafy.herehear.member.dto.response;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.entity.ProfileCharacter;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FollowResDto {

    private Long memberId;
    private String nickname;
//    private LocalDateTime followRegistDate;
    private ProfileCharacterResDto profileCharacter;
    private AchievementDto achievement;


}
