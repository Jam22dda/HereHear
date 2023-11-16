package com.ssafy.herehear.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatUtil {

    private TimeFormatUtil() {
    }

    public static String formatTime(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String YMDTimeFormat(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyMMdd"));
    }

}
