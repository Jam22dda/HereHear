package com.ssafy.herehear.music.util;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.response.SseResDto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HourFilterUtils {

    public static boolean findHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
        return hoursDifference > -3 && hoursDifference < 3 || hoursDifference >= 21 || hoursDifference <= -21;
    }

    public static boolean checkHour(List<SseResDto> sseResDto){
        // 현재 시간에서 '시'만 추출
        int currentHour = LocalDateTime.now().getHour();
        LocalTime timeWithHourOnly = LocalTime.of(currentHour, 0);

        long hoursDifference = ChronoUnit.HOURS.between(timeWithHourOnly, sseResDto.get(0).getCreateTime());

        return hoursDifference > -3 && hoursDifference < 3 || hoursDifference >= 21 || hoursDifference <= -21;
    }

    public static boolean beforeHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
        return hoursDifference == -3 || hoursDifference == 20;
    }

    public static boolean afterHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
        return hoursDifference == 2 || hoursDifference == -21;
    }

    public static long hoursDifference(RegisteredMusic findRegisteredMusic){
        LocalTime currentTime = LocalDateTime.now().toLocalTime();
        return ChronoUnit.HOURS.between(currentTime, findRegisteredMusic.getCreateTime());
    }
}
