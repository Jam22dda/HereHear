package com.ssafy.herehear.music.util;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HourFilterUtils {

    public static boolean findHourFilter(RegisteredMusic findRegisteredMusic){
        long hoursDifference = hoursDifference(findRegisteredMusic);
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
