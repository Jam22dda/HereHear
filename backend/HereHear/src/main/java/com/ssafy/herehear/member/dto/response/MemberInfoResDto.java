package com.ssafy.herehear.member.dto.response;

import com.ssafy.herehear.entity.ProfileCharacter;
import lombok.Getter;

@Getter
public class MemberInfoResDto {
    private String nickname;
    private ProfileCharacter profileCharacter;
}
