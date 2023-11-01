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

@RestController
@RequiredArgsConstructor
@RequestMapping("/music")
@Slf4j
@CrossOrigin("*")
public class SearchMusicController {

    private final MusicService musicService;

    @GetMapping("/search")
    public DataResponse<List<MusicInfoResDto>> searchMusic(@RequestParam String keyword, @RequestParam(required = false) Integer limit) {
        if (keyword == null) throw new CustomException(ExceptionStatus.KEYWORD_NOT_FOUND);
        if (limit == null) limit = 10;

        List<MusicInfoResDto> musicInfoList = musicService.getMusicInfoList(keyword, limit);

        return new DataResponse<>("200", "검색 성공", musicInfoList);
    }

}
