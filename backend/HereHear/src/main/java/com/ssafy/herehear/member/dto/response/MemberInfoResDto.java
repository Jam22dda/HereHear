package com.ssafy.herehear.member.dto.response;

import com.ssafy.herehear.entity.ProfileCharacter;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoResDto {
    private String nickname;
    private ProfileCharacterResDto profileCharacter;
    private Long achievementId;
    private String provider;
}
