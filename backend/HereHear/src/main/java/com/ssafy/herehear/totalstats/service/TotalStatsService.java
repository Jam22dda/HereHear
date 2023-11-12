package com.ssafy.herehear.totalstats.service;

import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;

import java.util.List;

public interface TotalStatsService {

    List<TotalStatsLikesResDto> getLikesTop4();

}
