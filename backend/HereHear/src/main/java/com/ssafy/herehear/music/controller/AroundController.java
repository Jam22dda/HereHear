package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.response.AroundMusicResDto;
import com.ssafy.herehear.music.service.AroundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/around")
@RequiredArgsConstructor
public class AroundController {

    private final AroundService aroundService;

    @GetMapping("/list")
    public DataResponse<List<AroundMusicResDto>> aroundMusicList(@RequestParam double lat, @RequestParam double lng) {
        return new DataResponse<>("200", ConstantsUtil.AROUND_MUSIC, aroundService.getAroundMusicList(lat, lng));
    }

    @PostMapping("/search")
    public DataResponse<List<AroundMusicResDto>> aroundSearchMusic(@RequestBody @Valid AroundSearchReqDto aroundSearchReqDto) {
        return new DataResponse<>("200", ConstantsUtil.AROUND_MUSIC_SEARCH, aroundService.getAroundSearchMusic(aroundSearchReqDto));
    }

}
