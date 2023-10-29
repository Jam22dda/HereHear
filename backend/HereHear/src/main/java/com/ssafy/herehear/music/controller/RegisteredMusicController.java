package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.response.ResponseService;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
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
    private final ResponseService responseService;

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
        return new DataResponse<>("200", "음악 상세 조회", registeredMusicService.getRegisteredMusicDetails(memberId,registeredMusicId));
    }

    @GetMapping("/list")
    public DataResponse<List<RegisteredMusicResDto>> getRegisteredMusicList() {
        return new DataResponse<>("200", "전체 음악 조회", registeredMusicService.getRegisteredMusicList());
    }
//
//    @GetMapping("/myList")
//    public DataResponse<List<RegisteredMusicResDto>> getMyMusicList(@RequestHeader("Member-id") Long memberId) {
//        log.info("[내 음악 전체 조회 param] memberId: "+memberId);
//
//        return new DataResponse<>("200", "내 음악 전체 조회", registeredMusicService.getMyMusicList(memberId));
//    }
//
//    @PutMapping("/myList/{registeredMusicId}")
//    public CommonResponse updateMyMusicList(@RequestHeader("Member-id") Long memberId, @PathVariable Long registeredMusicId) {
//        log.info("[등록한 음악 삭제(수정) param] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);
//
//        registeredMusicService.updateMusic(memberId, registeredMusicId);
//
//        return new CommonResponse("200", "등록한 음악 삭제");
//    }

//    //상황에 따른 음악 조회
//    @GetMapping("/ocation")
//
//    //재상 음악 조회
//    @GetMapping("/play/list")
//

}
