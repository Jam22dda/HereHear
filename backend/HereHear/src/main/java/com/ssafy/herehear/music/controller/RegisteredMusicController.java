package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicController {

    private final RegisteredMusicService registeredMusicService;

    @PostMapping
    public CommonResponse registerMusic(@RequestHeader("Member-id") Long memberId, @RequestBody RegisteredMusicReqDto registeredMusicReqDto) {
        log.info("[음악 등록] memberId: "+memberId+", registeredMusicReqDto: "+registeredMusicReqDto);

        registeredMusicService.registerMusic(memberId, registeredMusicReqDto);

        return new CommonResponse("200", "음악 등록 성공");
    }

    @GetMapping("/{musicId}")
    public DataResponse<RegisteredMusic> getMusic(@PathVariable long musicId) {
        log.info("[음악 조회] musicId: "+musicId);

        log.info("[음악 조회] findMusic: "+registeredMusicService.getMusic(musicId).getComment());
        return new DataResponse<>();
    }

    @GetMapping("/list")
    public ResponseEntity<Void> getMusicList() {
        log.info("[음악 전체 조회] findMusicList: "+registeredMusicService.getMusicList());
        return ResponseEntity.ok().build();
    }

//    //상황에 따른 음악 조회
//    @GetMapping("/ocation")
//
//    //재상 음악 조회
//    @GetMapping("/play/list")
//
//    //등록한 음악 조회
//    @GetMapping("/registered")
//
//    //등록한 음악 삭제(수정)
//    @PutMapping("/registered/{musicId}")

}
