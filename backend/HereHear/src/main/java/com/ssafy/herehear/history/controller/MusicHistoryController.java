package com.ssafy.herehear.history.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.history.dto.request.MusicRegisteredIdReqDto;
import com.ssafy.herehear.history.dto.response.PlayRegisteredMusicResDto;
import com.ssafy.herehear.history.service.MusicHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class MusicHistoryController {

    private final MusicHistoryService musicHistoryService;

    @PostMapping
    public CommonResponse playRegisteredMusic(Authentication authentication, @RequestBody MusicRegisteredIdReqDto req) {
        Long memberId = Long.parseLong(authentication.getName());
        musicHistoryService.registerPlayMusic(memberId, req.getRegisteredMusicId());
        return new CommonResponse("200", "최근 들은 음악 등록");
    }

    @DeleteMapping
    public CommonResponse playRegisteredMusicDelete(Authentication authentication, @RequestBody MusicRegisteredIdReqDto req){
        Long memberId = Long.parseLong(authentication.getName());
        musicHistoryService.deletePlayMusic(memberId, req.getRegisteredMusicId());
        return new CommonResponse("200", "최근 들은 음악 삭제");
    }

    @GetMapping("/list")
    public DataResponse<List<PlayRegisteredMusicResDto>> playRegisteredMusicList(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        return new DataResponse<>("200", "최근 들은 음악 조회", musicHistoryService.getMusicHistoryList(memberId));
    }

}
