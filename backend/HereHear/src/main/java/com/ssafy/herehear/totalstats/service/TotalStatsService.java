package com.ssafy.herehear.totalstats.service;

import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsMusicResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsTagsResDto;

import java.util.List;

public interface TotalStatsService {

    List<TotalStatsLikesResDto> getLikeCountTop4();

    List<TotalStatsTagsResDto> getTagsTop5();

    TotalStatsMusicResDto getTopHistoryMusic();
}
