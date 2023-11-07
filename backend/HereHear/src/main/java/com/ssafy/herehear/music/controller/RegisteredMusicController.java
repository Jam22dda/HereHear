package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.UpdateMusicIdReqDto;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import com.ssafy.herehear.music.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class RegisteredMusicController {

    private final RegisteredMusicService registeredMusicService;
    private final SseService sseService;

    @PostMapping
    public CommonResponse registerMusic(Authentication authentication, @RequestBody RegisterMusicReqDto req) {
        Long memberId = Long.parseLong(authentication.getName());
        List<SseResDto> sseResDto = registeredMusicService.registerMusic(memberId, req);
        sseService.notify(sseResDto);
        return new CommonResponse("200", "음악 등록");
    }

    @GetMapping("/tag")
    public DataResponse<List<OccasionResDto>> readTag() {
        return new DataResponse<>("200", "전체 태그 조회", registeredMusicService.getAllTags());
    }

    @GetMapping("/{registeredMusicId}")
    public DataResponse<RegisteredMusicDetailsResDto> registeredMusicDetails(Authentication authentication, @PathVariable long registeredMusicId) {
        Long memberId = Long.parseLong(authentication.getName());
        return new DataResponse<>("200", "음악 상세 조회", registeredMusicService.getRegisteredMusicDetails(memberId,registeredMusicId));
    }

    @PutMapping
    public CommonResponse updateMyRegisteredMusic(Authentication authentication, @RequestBody UpdateMusicIdReqDto req) {
        Long memberId = Long.parseLong(authentication.getName());
        List<SseResDto> sseResDto = registeredMusicService.updateMyRegisteredMusic(memberId, req.getRegisteredMusicId());
        sseService.notify(sseResDto);
        return new CommonResponse("200", "등록한 음악 삭제");
    }

    @GetMapping("/list")
    public DataResponse<List<RegisteredMusicResDto>> registeredMusicList() {
        return new DataResponse<>("200", "전체 음악 조회", registeredMusicService.getRegisteredMusicList());
    }

    @GetMapping("/my/list")
    public DataResponse<List<MyRegisteredMusicResDto>> myRegisteredMusicList(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        return new DataResponse<>("200", "내가 등록한 음악 조회", registeredMusicService.getMyRegisteredMusicList(memberId));
    }

    @GetMapping("/list/{memberId}")
    public DataResponse<List<MyRegisteredMusicResDto>> memberRegisteredMusicList(@PathVariable Long memberId) {
        return new DataResponse<>("200", "다른 유저가 등록한 음악 조회", registeredMusicService.getMyRegisteredMusicList(memberId));
    }


}
