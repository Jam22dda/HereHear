package com.ssafy.herehear.member.dto.response;

import com.ssafy.herehear.entity.ProfileCharacter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FollowResDto {

    private Long memberId;
    private String nickname;
//    private LocalDateTime followRegistDate;
    private ProfileCharacterResDto profileCharacter;

}
