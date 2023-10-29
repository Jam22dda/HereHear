package com.ssafy.herehear.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionStatus {

    /* 예시 */
    EXCEPTION("-9999", "예외가 발생하였습니다."),

    /**
     * 회원 관련 STATUS CODE
     */
    TOKEN_EXPIRED("-1000", "토큰이 만료되었습니다."),
    REFRESH_TOKEN_EXPIRED("-1001", "토큰이 만료되었습니다."),
    TOKEN_NOT_FOUND_IN_COOKIE("-1002", "토큰이 없습니다."),

    MEMBER_NOT_FOUND("-2000", "사용자를 찾을 수 없습니다."),

    /**
     * 음악악 관련 STATUS CODE
     */
    NOT_FOUND_REGISTERED_MUSIC("-3000","등록된 음악을 찾을 수 없습니다."),
    NOT_FOUND_MUSIC("-3001","음악을 찾을 수 없습니다."),
    DUPLICATE_MUSIC("-3002","중복된 음악입니다."),
    NOT_FOUND_OCCASION("-3010","상황 태그를 찾을 수 없습니다."),
    NOT_FOUND_HISTORY_MUSIC("-3100", "최근 들은 음악을 찾을 수 없습니다."),
    NOT_FOUND_LIKE_MUSIC("-3200", "좋아요 한 음악을 찾을 수 없습니다."),

    /**
     * 업적 관련 STATUS CODE
     */
    BORDER_CODE_NOT_FOUND("-4001", "테두리를 찾을 수 없습니다."),
    TITLE_CODE_NOT_FOUND("-4002", "타이틀을 찾을 수 없습니다.");


    private final String code;
    private final String message;
}
