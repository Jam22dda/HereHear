package com.ssafy.herehear.totalstats.service;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;
import com.ssafy.herehear.totalstats.mapper.TotalStatsMapper;
import com.ssafy.herehear.totalstats.repository.TotalStatsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TotalStatsServiceImpl implements TotalStatsService {

    private final TotalStatsRepository totalStatsRepository;
    private final TotalStatsMapper totalStatsMapper;

    @Override
    @Transactional
    public List<TotalStatsLikesResDto> getLikesTop4() {
        log.info("[{}]", ConstantsUtil.TOTAL_STATS_LIKES);

        List<TotalStatsLikesResDto> totalStatsLikesResDtos = totalStatsRepository.findByLikesSort().stream()
                .map(tuple -> totalStatsMapper.toTotalStatsLikesResDto(tuple.get(0, RegisteredMusic.class), tuple.get(1, Long.class)))
                .toList();
        log.info("[{}] 성공: {}", ConstantsUtil.TOTAL_STATS_LIKES, totalStatsLikesResDtos);

        return totalStatsLikesResDtos;
    }
}
