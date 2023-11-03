package com.ssafy.herehear.like.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.like.service.LikeMusicService;
import com.ssafy.herehear.like.dto.request.MusicRegisteredIdReqDto;
import com.ssafy.herehear.like.dto.response.LikeRegisteredMusicResDto;
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
    public CommonResponse registerlikeMusic(Authentication authentication, @RequestBody MusicRegisteredIdReqDto req){
        Long memberId = Long.parseLong(authentication.getName());
        likeMusicService.registerlikeMusic(memberId,req.getRegisteredMusicId());
        return new CommonResponse("200","좋아요 등록 및 취소");
    }

    @GetMapping("/list")
    public DataResponse<List<LikeRegisteredMusicResDto>> likeMusicList(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        return new DataResponse<>("200", "좋아요 음악 목록 조회", likeMusicService.likeMusicList(memberId));
    }

}
