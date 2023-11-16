package com.ssafy.herehear.history.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.history.dto.request.MusicRegisteredIdReqDto;
import com.ssafy.herehear.history.dto.response.PlayRegisteredMusicResDto;
import com.ssafy.herehear.history.service.MusicHistoryService;
import jakarta.validation.Valid;
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
    public CommonResponse playRegisteredMusic(Authentication authentication, @RequestBody @Valid MusicRegisteredIdReqDto req) {
        musicHistoryService.registerPlayMusic(Long.parseLong(authentication.getName()), req.getRegisteredMusicId());
        return new CommonResponse("200", ConstantsUtil.HISTORY_REGISTER_MUSIC);
    }

    @DeleteMapping
    public CommonResponse playRegisteredMusicDelete(Authentication authentication, @RequestBody @Valid MusicRegisteredIdReqDto req){
        musicHistoryService.deletePlayMusic(Long.parseLong(authentication.getName()), req.getRegisteredMusicId());
        return new CommonResponse("200", ConstantsUtil.HISTORY_DELETE_MUSIC);
    }

    @GetMapping("/list")
    public DataResponse<List<PlayRegisteredMusicResDto>> playRegisteredMusicList(Authentication authentication) {
        return new DataResponse<>("200", ConstantsUtil.HISTORY_MUSIC_LIST,
                musicHistoryService.getMusicHistoryList(Long.parseLong(authentication.getName())));
    }

}
