package com.ssafy.herehear.totalstats.service.totalstatsServiceImpl;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsMusicResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsTagsResDto;
import com.ssafy.herehear.totalstats.mapper.TotalStatsMapper;
import com.ssafy.herehear.totalstats.repository.TotalStatsRepository;
import com.ssafy.herehear.totalstats.service.TotalStatsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TotalStatsServiceImpl implements TotalStatsService {

    private final TotalStatsRepository totalStatsRepository;
    private final TotalStatsMapper totalStatsMapper;

    @Override
    @Transactional
    public List<TotalStatsLikesResDto> getLikeCountTop4() {
        log.info("[{}]", ConstantsUtil.TOTAL_STATS_LIKES);

        List<TotalStatsLikesResDto> totalStatsLikesResDtos = totalStatsRepository.findByLikesSort(getStartOfWeek(), getEndOfWeek()).stream()
                .map(tuple -> totalStatsMapper.toTotalStatsLikesResDto(tuple.get(0, RegisteredMusic.class), tuple.get(1, Long.class)))
                .toList();
        log.info("[{}] 성공:  {}", ConstantsUtil.TOTAL_STATS_LIKES, totalStatsLikesResDtos);

        return totalStatsLikesResDtos;
    }

    @Override
    @Transactional
    public List<TotalStatsTagsResDto> getTagsTop5() {
        log.info("[{}]", ConstantsUtil.TOTAL_STATS_TAGS);

        List<TotalStatsTagsResDto> totalStatsLikesResDtos = totalStatsRepository.findByTagsSort(getStartOfWeek(), getEndOfWeek()).stream()
                .map(tuple -> totalStatsMapper.toTotalStatsTagsResDto(tuple.get(0, String.class), tuple.get(1, Long.class)))
                .toList();
        log.info("[{}]  성공: {}", ConstantsUtil.TOTAL_STATS_TAGS, totalStatsLikesResDtos);

        return totalStatsLikesResDtos;
    }

    @Override
    @Transactional
    public TotalStatsMusicResDto getTopHistoryMusic() {
        log.info("[{}]", ConstantsUtil.TOTAL_STATS_HISTORY_MUSIC);

        TotalStatsMusicResDto totalStatsMusicResDtos = totalStatsMapper.toTotalStatsMusicResDto(
                totalStatsRepository.findByTopHistoryMusic(getStartOfWeek(), getEndOfWeek()));
        log.info("[{}] 성공: {}", ConstantsUtil.TOTAL_STATS_HISTORY_MUSIC, totalStatsMusicResDtos);

        return totalStatsMusicResDtos;
    }

    public static LocalDateTime getStartOfWeek() {
        return getStartOfDay().minusWeeks(1).with(DayOfWeek.MONDAY).atStartOfDay();
    }

    public static LocalDateTime getEndOfWeek() {
        return getStartOfDay().minusWeeks(1).with(DayOfWeek.SUNDAY).atTime(23, 59, 59);
    }

    public static LocalDate getStartOfDay() {
        return LocalDate.now().with(DayOfWeek.SUNDAY);
    }

}
