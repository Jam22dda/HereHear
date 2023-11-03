package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.AroundMusicReqDto;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.response.AroundMusicResDto;
import com.ssafy.herehear.music.service.AroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/music/around")
@RequiredArgsConstructor
public class AroundController {

    private final AroundService aroundService;

    @GetMapping("/list")
    public DataResponse<List<AroundMusicResDto>> aroundMusicList(@RequestBody AroundMusicReqDto aroundMusicReqDto) {
        return new DataResponse<>("200", "주변 음악 조회", aroundService.getAroundMusicList(aroundMusicReqDto));
    }

    @GetMapping("/search")
    public DataResponse<List<AroundMusicResDto>> aroundSearchMusic(@RequestBody AroundSearchReqDto aroundSearchReqDto) {
        return new DataResponse<>("200", "주변 음악 검색", aroundService.getAroundSearchMusic(aroundSearchReqDto));
    }

}
