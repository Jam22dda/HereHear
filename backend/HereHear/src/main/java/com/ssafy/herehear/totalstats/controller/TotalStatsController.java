package com.ssafy.herehear.totalstats.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;
import com.ssafy.herehear.totalstats.service.TotalStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/totalstats")
@RequiredArgsConstructor
public class TotalStatsController {

    private final TotalStatsService totalStatsService;

    @GetMapping("/likes")
    public DataResponse<List<TotalStatsLikesResDto>> aroundMusicList() {
        return new DataResponse<>("200", ConstantsUtil.TOTAL_STATS_LIKES, totalStatsService.getLikesTop4());
    }



}
