package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicController {

    private final RegisteredMusicService registeredMusicService;

    @PostMapping
    public CommonResponse registerMusic(@RequestHeader("Member-id") Long memberId, @RequestBody RegisteredMusicReqDto registeredMusicReqDto) {
        log.info("[음악 등록 param] memberId: "+memberId+", registeredMusicReqDto: "+registeredMusicReqDto);

        return registeredMusicService.registerMusic(memberId, registeredMusicReqDto);
    }

    @GetMapping("/{registeredMusicId}")
    public DataResponse<RegisteredMusicResDto> getMusic(@PathVariable long registeredMusicId) {
        log.info("[음악 상세 조회 param] musicId: "+registeredMusicId);

        return registeredMusicService.getRegisteredMusic(registeredMusicId);
    }

    @GetMapping("/list")
    public DataResponse<List<RegisteredMusicResDto>> getMusicList() {
        return registeredMusicService.getMusicList();
    }

    @GetMapping("/myList")
    public DataResponse<List<RegisteredMusicResDto>> getMyMusicList(@RequestHeader("Member-id") Long memberId) {
        log.info("[내 음악 전체 조회 param] memberId: "+memberId);

        return registeredMusicService.getMyMusicList(memberId);
    }

    @PutMapping("/myList/{musicId}")
    public CommonResponse updateMyMusicList(@RequestHeader("Member-id") Long memberId) {
        log.info("[등록한 음악 삭제(수정) param] memberId: "+memberId);

        return registeredMusicService.updateMusic(memberId);
    }

//    //상황에 따른 음악 조회
//    @GetMapping("/ocation")
//
//    //재상 음악 조회
//    @GetMapping("/play/list")
//

}
