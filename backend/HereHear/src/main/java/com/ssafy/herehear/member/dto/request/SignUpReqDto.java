package com.ssafy.herehear.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpReqDto {
    private Long memberId;
    private String nickname;
    private Long profileCharacterCode;
}
