package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import com.ssafy.herehear.music.dto.response.MusicInfoResDto;
import com.ssafy.herehear.music.service.MusicService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/music")
@Slf4j
@Validated
public class SearchMusicController {

    private final MusicService musicService;

    @GetMapping("/search")
    public DataResponse<Map<String, Object>> searchMusic(@RequestParam @NotEmpty(message = "검색어는 필수 입력값 입니다") String keyword,
                                                         @RequestParam(required = false) Integer limit,
                                                         @RequestParam(required = false) Integer page) {
        log.info("[음악 검색] keyword: {}, limit: {}, page: {}, time {} ", keyword, limit, page, TimeFormatUtil.formatTime(LocalDateTime.now()));

        // 기본 출력 개수 설정
        if (limit == null) limit = 10;

        // 페이징 처리
        int offset = 0;
        if (page == null) page = 1;
        else offset = (page - 1) * limit;

        List<MusicInfoResDto> musicInfoList = musicService.getMusicInfoList(keyword, limit, offset);

        Map<String, Object> result = Map.of(
                "musicInfoList", musicInfoList,
                "page", page,
                "limit", limit
        );

        return new DataResponse<>("200", page + "페이지 검색 성공", result);
    }

}
