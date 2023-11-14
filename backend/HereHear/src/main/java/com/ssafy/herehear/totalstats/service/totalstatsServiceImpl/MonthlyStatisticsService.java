package com.ssafy.herehear.totalstats.service.totalstatsServiceImpl;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.totalstats.repository.MonthlyStatisticsRepository;
import com.ssafy.herehear.totalstats.repository.RegisteredMusicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class MonthlyStatisticsService {

    private final RegisteredMusicRepository registeredMusicRepository;
    private final MonthlyStatisticsRepository monthlyStatisticsRepository;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateIsDeletedToFalse() {
        log.info("매일 지난 한 달간의 음악 삭제 실행");

        for (RegisteredMusic registeredMusic : monthlyStatisticsRepository.findByMonthlyRegisteredMusics()) {
            registeredMusic.updateRegisteredMusic(false);
            registeredMusicRepository.save(registeredMusic);
        }

        log.info("매일 지난 한 달간의 음악 삭제 성공");
    }

}
