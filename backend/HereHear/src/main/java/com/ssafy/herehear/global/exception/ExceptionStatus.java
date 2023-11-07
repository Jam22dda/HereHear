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
    FOLLOW_NOT_FOUND("-2001", "팔로우를 찾을 수 없습니다."),
    PROFILE_CHARACTER_NOT_FOUND("-2002", "프로필 캐릭터를 찾을 수 없습니다."),
    MEMBER_ALREADY_SIGNED("-2003", "이미 가입한 사용자입니다."),
    MEMBER_IS_DELETED("-2004", "탈퇴한 회원입니다."),
    NICKNAME_ALREADY_USED("-2121", "사용 중인 닉네임입니다."),
    PERSONAL_CODE_IS_INVALID("-2500", "WEAR OS 의 개인코드가 유효하지 않습니다."),

    /**
     * 음악악 관련 STATUS CODE
     */
    NOT_FOUND_REGISTERED_MUSIC("-3000", "등록된 음악을 찾을 수 없습니다."),
    NOT_FOUND_MUSIC("-3001", "음악을 찾을 수 없습니다."),
    DUPLICATE_MUSIC("-3002", "중복된 음악입니다."),
    KEYWORD_NOT_FOUND("-3003", "검색 키워드를 입력해주세요."),
    NOT_FOUND_OCCASION("-3010", "상황 태그를 찾을 수 없습니다."),
    NOT_FOUND_HISTORY_MUSIC("-3100", "최근 들은 음악을 찾을 수 없습니다."),
    NOT_FOUND_LIKE_MUSIC("-3200", "좋아요 한 음악을 찾을 수 없습니다."),
    JSON_PARSE_ERROR("-3300", "JOSN 결과를 파싱할 수 없습니다."),

    /**
     * 업적 관련 STATUS CODE
     */
    ACHIEVEMENT_NOT_FOUND("-4003", "업적을 찾을 수 업습니다."),

    /**
     * 파일 업로드 관련 STATUS CODE
     */
    FILE_NOT_FOUND("-4200", "파일을 찾을 수 없습니다."),
    FILE_UPLOAD_FAILED("-4201", "파일 업로드에 실패하였습니다.");


    private final String code;
    private final String message;
}
