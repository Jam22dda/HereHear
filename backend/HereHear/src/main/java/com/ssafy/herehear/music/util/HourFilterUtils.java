package com.ssafy.herehear.music.util;

import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class HourFilterUtils {

    public static boolean findHourFilter(RegisteredMusic findRegisteredMusic){
        LocalTime currentTime = LocalDateTime.now().toLocalTime();

        // 현재 시간으로부터 +3h 이내 또는 -3h 이내인 데이터만 필터링
        long hoursDifference = ChronoUnit.HOURS.between(currentTime, findRegisteredMusic.getCreateTime());
        log.info("getRegisteredMusicId: "+findRegisteredMusic.getRegisteredMusicId()+", hoursDifference: "+hoursDifference);
        return hoursDifference >= -3 && hoursDifference <= 3 || hoursDifference >= 21;
    }

}
