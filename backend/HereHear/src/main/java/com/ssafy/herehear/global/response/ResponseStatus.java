package com.ssafy.herehear.global.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    /* 공통 */
    RESOPNSE_SUCCESS("200", "요청에 성공했습니다.");

    private final String code;
    private final String message;
}
