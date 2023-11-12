package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.music.dto.request.UpdateMusicIdReqDto;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import com.ssafy.herehear.music.service.SseService;
import com.ssafy.herehear.music.util.HourFilterUtils;
import jakarta.validation.Valid;
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
    public CommonResponse registerMusic(Authentication authentication, @RequestBody @Valid RegisterMusicReqDto req) {
        List<SseResDto> sseResDto = registeredMusicService.registerMusic(Long.parseLong(authentication.getName()), req);
        sseService.notifyAllMembers(sseResDto);
        return new CommonResponse("200", ConstantsUtil.MUSIC_REGISTER);
    }

    @GetMapping("/tag")
    public DataResponse<List<OccasionResDto>> readTag() {
        return new DataResponse<>("200", ConstantsUtil.ALL_TAGS, registeredMusicService.getAllTags());
    }

    @GetMapping("/{registeredMusicId}")
    public DataResponse<RegisteredMusicDetailsResDto> registeredMusicDetails(Authentication authentication, @PathVariable long registeredMusicId) {
        return new DataResponse<>("200", ConstantsUtil.MUSIC_DETAILS,
                registeredMusicService.getRegisteredMusicDetails(Long.parseLong(authentication.getName()),registeredMusicId));
    }

    @PutMapping
    public CommonResponse updateMyRegisteredMusic(Authentication authentication, @RequestBody @Valid UpdateMusicIdReqDto req) {
        List<SseResDto> sseResDto = registeredMusicService.updateMyRegisteredMusic(Long.parseLong(authentication.getName()), req.getRegisteredMusicId());
        if(HourFilterUtils.checkTime(sseResDto))
            sseService.notifyAllMembers(sseResDto);
        return new CommonResponse("200", ConstantsUtil.MUSIC_DELETE);
    }

    @GetMapping("/list")
    public DataResponse<List<RegisteredMusicMapResDto>> registeredMusicList() {
        return new DataResponse<>("200", ConstantsUtil.ALL_REGISTERED_MUSIC, registeredMusicService.getRegisteredMusicList());
    }

    @GetMapping("/my/list")
    public DataResponse<List<MyRegisteredMusicResDto>> myRegisteredMusicList(Authentication authentication) {
        return new DataResponse<>("200", ConstantsUtil.MY_REGISTERED_MUSIC,
                registeredMusicService.getMyRegisteredMusicList(Long.parseLong(authentication.getName()), ConstantsUtil.MY_REGISTERED_MUSIC));
    }

    @GetMapping("/list/{memberId}")
    public DataResponse<List<MyRegisteredMusicResDto>> memberRegisteredMusicList(@PathVariable Long memberId) {
        return new DataResponse<>("200", ConstantsUtil.OTHER_MEMBER_REGISTERED_MUSIC,
                registeredMusicService.getMyRegisteredMusicList(memberId, ConstantsUtil.OTHER_MEMBER_REGISTERED_MUSIC));
    }

}
