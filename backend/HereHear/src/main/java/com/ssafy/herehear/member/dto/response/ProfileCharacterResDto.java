package com.ssafy.herehear.member.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileCharacterResDto {
    private Long profileCharacterId;
    private String characterName;
    private String characterImage;
}
