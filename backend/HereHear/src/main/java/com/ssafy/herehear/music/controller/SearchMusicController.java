package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.response.MusicInfoResDto;
import com.ssafy.herehear.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/music")
@Slf4j
@CrossOrigin("*")
public class SearchMusicController {

    private final MusicService musicService;

    @GetMapping("/search")
    public DataResponse<Map<String, Object>> searchMusic(@RequestParam String keyword,
                                                         @RequestParam(required = false) Integer limit,
                                                         @RequestParam(required = false) Integer page) {
        // 검색어가 비었다면 에러
        if (keyword == null) throw new CustomException(ExceptionStatus.KEYWORD_NOT_FOUND);

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

        return new DataResponse<>("200", page + "검색 성공", result);
    }

}
