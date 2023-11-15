package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.ChangeVolumeReqDto;
import com.ssafy.herehear.music.dto.request.PlayMusicReqDto;
import com.ssafy.herehear.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/spotify")
@RequiredArgsConstructor
public class SpotifyController {
    private final MusicService musicService;

    @GetMapping("/device")
    public DataResponse getDevice(Authentication authentication) {
        long memberId = Long.parseLong(authentication.getName());
        String ans = musicService.getDeviceId(memberId);
        return new DataResponse("200", "device id 반환을 성공하였습니다", ans);
    }

    @GetMapping("/token")
    public DataResponse getToken(Authentication authentication) {
        long memberId = Long.parseLong(authentication.getName());
        musicService.updateMemberAccessToken(memberId);
        String accessToken = musicService.getToken(memberId);
        return new DataResponse("200", "spotify accessToken 반환을 성공하였습니다", accessToken);
    }

    @PostMapping("/play")
    public CommonResponse playMusic(@RequestBody PlayMusicReqDto req, Authentication authentication) {
        long memberId = Long.parseLong(authentication.getName());
        musicService.startMusic(memberId, req.getTrackId());
        return new CommonResponse("200", "노래 재생을 성공하였습니다");
    }

    @GetMapping("/pause")
    public DataResponse playMusic(Authentication authentication) {
        long memberId = Long.parseLong(authentication.getName());
        int progressMs = musicService.pauseMusic(memberId);
        return new DataResponse("200", "노래 정지를 성공하였습니다", progressMs);
    }

    @PostMapping("/add")
    public CommonResponse addMusic(@RequestBody PlayMusicReqDto req, Authentication authentication) {
        long memberId = Long.parseLong(authentication.getName());
        musicService.addMusic(memberId, req.getTrackId());
        return new CommonResponse("200", "재생 목록에 음악 추가를 성공하였습니다");
    }

    @PostMapping("/volume")
    public CommonResponse changeVolume(@RequestBody ChangeVolumeReqDto req, Authentication authentication) {
        long memberId = Long.parseLong(authentication.getName());
        musicService.changeVolume(memberId, req.getVolume());
        return new CommonResponse("200", "볼륨 조절을 성공하였습니다");

    }
}
