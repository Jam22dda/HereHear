package com.ssafy.herehear.member.dto.request;

import lombok.*;

@Getter
public class SignUpReqDto {
    private Long memberId;
    private String nickname;
    private Long profileCharacterCode;
}
