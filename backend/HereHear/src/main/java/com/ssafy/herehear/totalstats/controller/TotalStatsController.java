package com.ssafy.herehear.totalstats.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.totalstats.dto.TotalStatsMusicResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsTagsResDto;
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

    @GetMapping("/likecount")
    public DataResponse<List<TotalStatsLikesResDto>> likeCountTop4() {
        return new DataResponse<>("200", ConstantsUtil.TOTAL_STATS_LIKES, totalStatsService.getLikeCountTop4());
    }

    @GetMapping("/tags")
    public DataResponse<List<TotalStatsTagsResDto>> tagsTop5() {
        return new DataResponse<>("200", ConstantsUtil.TOTAL_STATS_TAGS, totalStatsService.getTagsTop5());
    }

    @GetMapping("/music")
    public DataResponse<TotalStatsMusicResDto> topHistoryMusic() {
        return new DataResponse<>("200", ConstantsUtil.TOTAL_STATS_HISTORY_MUSIC, totalStatsService.getTopHistoryMusic());
    }

}
