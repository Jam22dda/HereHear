package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
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
        log.info("[음악 등록 param] memberId: "+memberId+", registeredMusicReqDto: "+ registerMusicReqDto);

        registeredMusicService.registerMusic(memberId, registerMusicReqDto);

        return new CommonResponse("200", "음악 등록");
    }

    @GetMapping("/tag")
    public DataResponse<List<Occasion>> readTag() {
        return new DataResponse<>("200", "전체 태그 조회", registeredMusicService.getTag());
    }

    @GetMapping("/{registeredMusicId}")
    public DataResponse<RegisteredMusicDetailsResDto> registeredMusicDetails(@RequestHeader("Member-id") Long memberId, @PathVariable long registeredMusicId) {
        log.info("[음악 상세 조회 param] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

        return new DataResponse<>("200", "음악 상세 조회", registeredMusicService.getRegisteredMusicDetails(memberId,registeredMusicId));
    }

    @GetMapping("/list")
    public DataResponse<List<RegisteredMusicResDto>> registeredMusicList() {
        return new DataResponse<>("200", "전체 음악 조회", registeredMusicService.getRegisteredMusicList());
    }

    @PutMapping("/my/list/{registeredMusicId}")
    public CommonResponse updateMyRegisteredMusic(@RequestHeader("Member-id") Long memberId, @PathVariable Long registeredMusicId) {
        log.info("[등록한 음악 삭제(수정) param] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

        registeredMusicService.updateMyRegisteredMusic(memberId, registeredMusicId);

        return new CommonResponse("200", "등록한 음악 삭제");
    }

    @GetMapping("/my/list")
    public DataResponse<List<MyRegisteredMusicResDto>> myRegisteredMusicList(@RequestHeader("Member-id") Long memberId) {
        return new DataResponse<>("200", "내가 등록한 음악 조회", registeredMusicService.getMyRegisteredMusicList(memberId));
    }

    @PostMapping("/play/{registeredMusicId}")
    public CommonResponse playRegisteredMusic(@RequestHeader("Member-id") Long memberId, @PathVariable Long registeredMusicId) {
        log.info("[들은 음악 등록 param] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

        registeredMusicService.registerPlayMusic(memberId, registeredMusicId);

        return new CommonResponse("200", "최근 들은 음악 등록");
    }

    @DeleteMapping("/play/{registeredMusicId}")
    public CommonResponse playRegisteredMusicDelete(@RequestHeader("Member-id") Long memberId, @PathVariable Long registeredMusicId){
        log.info("[들은 음악 등록 param] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

        registeredMusicService.deletePlayMusic(memberId, registeredMusicId);

        return new CommonResponse("200", "최근 들은 음악 삭제");
    }

//    @GetMapping("/play/list")
//    public DataResponse<List<LikeRegisteredMusicResDto>> playRegisteredMusicList(@RequestHeader("Member-id") Long memberId) {
//        return new DataResponse<>("200", "최근 들은 음악 조회", registeredMusicService.getPlayMusicList(memberId));
//    }


}
