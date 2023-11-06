package com.ssafy.herehear.music.util;

import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HourFilterUtils {

    private static final LocalTime currentTime = LocalDateTime.now().toLocalTime();

    public static boolean findHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
        return hoursDifference > -3 && hoursDifference < 3 || hoursDifference >= 21 || hoursDifference <= -21;//-3h ~ +3h
    }

    public static boolean beforeHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
        return hoursDifference == -3 || hoursDifference == 20;//-4h ~ -3h
    }

    public static boolean afterHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
        return hoursDifference == 3 || hoursDifference == -20;//+3h ~ +4h
    }

    public static long hoursDifference(RegisteredMusic findRegisteredMusic){
        return ChronoUnit.HOURS.between(currentTime, findRegisteredMusic.getCreateTime());
    }
}
