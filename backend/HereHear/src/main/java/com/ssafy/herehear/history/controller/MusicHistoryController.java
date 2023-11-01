package com.ssafy.herehear.history.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.history.dto.request.MusicRegisteredIdReqDto;
import com.ssafy.herehear.history.dto.response.PlayRegisteredMusicResDto;
import com.ssafy.herehear.history.service.MusicHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class MusicHistoryController {

    private final MusicHistoryService musicHistoryService;

    @PostMapping
    public CommonResponse playRegisteredMusic(@RequestHeader("Member-id") Long memberId, @RequestBody MusicRegisteredIdReqDto req) {
        musicHistoryService.registerPlayMusic(memberId, req.getRegisteredMusicId());
        return new CommonResponse("200", "최근 들은 음악 등록");
    }

    @DeleteMapping
    public CommonResponse playRegisteredMusicDelete(@RequestHeader("Member-id") Long memberId, @RequestBody MusicRegisteredIdReqDto req){
        musicHistoryService.deletePlayMusic(memberId, req.getRegisteredMusicId());
        return new CommonResponse("200", "최근 들은 음악 삭제");
    }

    @GetMapping("/list")
    public DataResponse<List<PlayRegisteredMusicResDto>> playRegisteredMusicList(@RequestHeader("Member-id") Long memberId) {
        return new DataResponse<>("200", "최근 들은 음악 조회", musicHistoryService.getMusicHistoryList(memberId));
    }

}
