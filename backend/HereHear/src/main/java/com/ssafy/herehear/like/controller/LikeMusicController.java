package com.ssafy.herehear.like.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.like.service.LikeMusicService;
import com.ssafy.herehear.like.dto.request.MusicRegisteredIdReqDto;
import com.ssafy.herehear.like.dto.response.LikeRegisteredMusicResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeMusicController {

    private final LikeMusicService likeMusicService;

    @PostMapping
    public CommonResponse registerlikeMusic(Authentication authentication, @RequestBody @Valid MusicRegisteredIdReqDto req) {
        likeMusicService.registerlikeMusic(Long.parseLong(authentication.getName()), req.getRegisteredMusicId());
        return new CommonResponse("200", ConstantsUtil.LIKE_REGISTER_DELETE);
    }

    @GetMapping("/list")
    public DataResponse<List<LikeRegisteredMusicResDto>> likeMusicList(Authentication authentication) {
        return new DataResponse<>("200", ConstantsUtil.LIKE_LIST,
                likeMusicService.likeMusicList(Long.parseLong(authentication.getName()), ConstantsUtil.LIKE_LIST));
    }

    @GetMapping("/list/{memberId}")
    public DataResponse<List<LikeRegisteredMusicResDto>> likeMemberMusicList(@PathVariable Long memberId) {
        return new DataResponse<>("200", ConstantsUtil.OTHER_MEMBER_LIKE_MUSIC,
                likeMusicService.likeMusicList(memberId, ConstantsUtil.OTHER_MEMBER_LIKE_MUSIC));
    }

}
