package com.ssafy.herehear.global.util;

public final class ConstantsUtil {
    private ConstantsUtil() {
        throw new AssertionError("This class should not be instantiated");
    }

    /**
     * TotalStats 로그 메시지 타이틀
     */
    public static final String TOTAL_STATS_LIKES = "일주일 동안 받은 좋아요 top 4 음악 조회";
    public static final String TOTAL_STATS_TAGS = "일주일 동안 태그된 단어 top 5 조회";
    public static final String TOTAL_STATS_HISTORY_MUSIC = "가장 많이 재생된 음악 조회";

    /**
     * Like 로그 메시지 타이틀
     */
    public static final String LIKE_REGISTER_DELETE = "좋아요 등록 및 삭제";
    public static final String LIKE_LIST = "좋아요 음악 목록 조회";
    public static final String OTHER_MEMBER_LIKE_MUSIC = "다른 유저의 좋아요한 음악 조회";

    /**
     * History 로그 메시지 타이틀
     */
    public static final String HISTORY_REGISTER_MUSIC = "최근 들은 음악 등록";
    public static final String HISTORY_DELETE_MUSIC = "최근 들은 음악 삭제";
    public static final String HISTORY_MUSIC_LIST = "최근 들은 음악 조회";

    /**
     * SSE 로그 메시지 타이틀
     */
    public static final String SSE_SCHEDULER = "SSE Scheduler";
    public static final String SSE_EVENT = "SSE 이벤트";

    /**
     * Music 로그 메시지 타이틀
     */
    public static final String MUSIC_REGISTER = "음악 등록";
    public static final String ALL_TAGS = "전체 태그 조회";
    public static final String MUSIC_DETAILS = "음악 상세 조회";
    public static final String MUSIC_DELETE = "등록한 음악 삭제";
    public static final String ALL_REGISTERED_MUSIC = "전체 음악 조회";
    public static final String MY_REGISTERED_MUSIC = "내가 등록한 음악 조회";
    public static final String OTHER_MEMBER_REGISTERED_MUSIC = "다른 유저가 등록한 음악 조회";

    /**
     * Music 로그 메시지 타이틀
     */
    public static final String AROUND_MUSIC = "주변 음악 조회";
    public static final String AROUND_MUSIC_SEARCH = "주변 음악 검색";

}
