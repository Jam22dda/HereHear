package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.response.AroundMusicResDto;
import com.ssafy.herehear.music.service.AroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/around")
@RequiredArgsConstructor
public class AroundController {

    private final AroundService aroundService;

    @GetMapping("/list/{lat}/{lng}")
    public DataResponse<List<AroundMusicResDto>> aroundMusicList(@PathVariable String lat, @PathVariable String lng) {
        return new DataResponse<>("200", "주변 음악 조회", aroundService.getAroundMusicList(Double.parseDouble(lat), Double.parseDouble(lng)));
    }

    @GetMapping("/search")
    public DataResponse<List<AroundMusicResDto>> aroundSearchMusic(@RequestBody AroundSearchReqDto aroundSearchReqDto) {
        return new DataResponse<>("200", "주변 음악 검색", aroundService.getAroundSearchMusic(aroundSearchReqDto));
    }

}
