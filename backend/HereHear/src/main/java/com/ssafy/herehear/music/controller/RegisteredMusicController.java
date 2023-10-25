package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class RegisteredMusicController {

    private final RegisteredMusicService registeredMusicService;

    //음악 등록
    @PostMapping
    public ResponseEntity<Void> registerMusic(@RequestHeader("Member-id") Long memberId, @RequestBody RegisteredMusicReqDto registeredMusicReqDto) {
        registeredMusicService.registerMusic(memberId, registeredMusicReqDto);
        return ResponseEntity.ok().build();
    }
//
//    //음악 조회
//    @GetMapping("/{musicId}")
//    public ResponseEntity<RegisteredMusic> getMusic(@RequestHeader("Member-id") Long memberId, @PathVariable long musicId) {
////        return registeredMusicService.getMusic(memberId, musicId);
//        return ResponseEntity.ok().build();
//    }
//
//    //전체 음악 조회
//    @GetMapping("/list")
//    public ResponseEntity<Void> getMusicList() {
////        registeredMusicService.getMusicList();
//        return ResponseEntity.ok().build();
//    }

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
