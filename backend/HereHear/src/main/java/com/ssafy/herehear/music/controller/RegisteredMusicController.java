package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.PlayMusicReqDto;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.LikeRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.MyRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicDetailsResDto;
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
    public CommonResponse registerMusic(@RequestHeader("Member-id") Long memberId, @RequestBody RegisterMusicReqDto registerMusicReqDto) {
        registeredMusicService.registerMusic(memberId, registerMusicReqDto);
        return new CommonResponse("200", "음악 등록");
    }

    @GetMapping("/tag")
    public DataResponse<List<Occasion>> readTag() {
        return new DataResponse<>("200", "전체 태그 조회", registeredMusicService.getTag());
    }

    @GetMapping("/{registeredMusicId}")
    public DataResponse<RegisteredMusicDetailsResDto> registeredMusicDetails(@RequestHeader("Member-id") Long memberId, @PathVariable long registeredMusicId) {
        return new DataResponse<>("200", "음악 상세 조회", registeredMusicService.getRegisteredMusicDetails(memberId,registeredMusicId));
    }

    @GetMapping("/list")
    public DataResponse<List<RegisteredMusicResDto>> registeredMusicList() {
        return new DataResponse<>("200", "전체 음악 조회", registeredMusicService.getRegisteredMusicList());
    }

    @PutMapping("/my/list")
    public CommonResponse updateMyRegisteredMusic(@RequestHeader("Member-id") Long memberId, @RequestBody PlayMusicReqDto playMusicReqDto) {
        registeredMusicService.updateMyRegisteredMusic(memberId, playMusicReqDto.getRegisteredMusicId());
        return new CommonResponse("200", "등록한 음악 삭제");
    }

    @GetMapping("/my/list")
    public DataResponse<List<MyRegisteredMusicResDto>> myRegisteredMusicList(@RequestHeader("Member-id") Long memberId) {
        return new DataResponse<>("200", "내가 등록한 음악 조회", registeredMusicService.getMyRegisteredMusicList(memberId));
    }

    @PostMapping("/play")
    public CommonResponse playRegisteredMusic(@RequestHeader("Member-id") Long memberId, @RequestBody PlayMusicReqDto playMusicReqDto) {
        registeredMusicService.registerPlayMusic(memberId, playMusicReqDto.getRegisteredMusicId());
        return new CommonResponse("200", "최근 들은 음악 등록");
    }

    @DeleteMapping("/play")
    public CommonResponse playRegisteredMusicDelete(@RequestHeader("Member-id") Long memberId, @RequestBody PlayMusicReqDto playMusicReqDto){
        registeredMusicService.deletePlayMusic(memberId, playMusicReqDto.getRegisteredMusicId());
        return new CommonResponse("200", "최근 들은 음악 삭제");
    }

    @GetMapping("/play/list")
    public DataResponse<List<LikeRegisteredMusicResDto>> playRegisteredMusicList(@RequestHeader("Member-id") Long memberId) {
        return new DataResponse<>("200", "최근 들은 음악 조회", registeredMusicService.getMusicHistoryList(memberId));
    }

}
