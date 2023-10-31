package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.AroundMusicReqDto;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.request.MusicRegisteredIdReqDto;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class RegisteredMusicController {

    private final RegisteredMusicService registeredMusicService;

    @PostMapping
    public CommonResponse registerMusic(@RequestHeader("Member-id") Long memberId, @RequestBody RegisterMusicReqDto req) {
        registeredMusicService.registerMusic(memberId, req);
        return new CommonResponse("200", "음악 등록");
    }

    @GetMapping("/tag")
    public DataResponse<List<OccasionResDto>> readTag() {
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
    public CommonResponse updateMyRegisteredMusic(@RequestHeader("Member-id") Long memberId, @RequestBody MusicRegisteredIdReqDto req) {
        registeredMusicService.updateMyRegisteredMusic(memberId, req.getRegisteredMusicId());
        return new CommonResponse("200", "등록한 음악 삭제");
    }

    @GetMapping("/my/list")
    public DataResponse<List<MyRegisteredMusicResDto>> myRegisteredMusicList(@RequestHeader("Member-id") Long memberId) {
        return new DataResponse<>("200", "내가 등록한 음악 조회", registeredMusicService.getMyRegisteredMusicList(memberId));
    }

    @GetMapping("/around/list")
    public DataResponse<List<AroundMusicResDto>> aroundMusicList(@RequestBody AroundMusicReqDto aroundMusicReqDto) {
        return new DataResponse<>("200", "주변 음악 조회", registeredMusicService.getAroundMusicList(aroundMusicReqDto));
    }

    @GetMapping("/around/search")
    public DataResponse<List<AroundMusicResDto>> aroundSearchMusic(@RequestBody AroundSearchReqDto aroundSearchReqDto) {
        return new DataResponse<>("200", "주변 음악 검색", registeredMusicService.getAroundSearchMusic(aroundSearchReqDto));
    }

}
