package com.ssafy.herehear.youtube.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.util.RedisUtils;
import com.ssafy.herehear.youtube.dto.YoutubePlayReqDto;
import com.ssafy.herehear.youtube.service.YoutubePlayListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor
public class YoutubeController {

    private final YoutubePlayListService youtubePlayListService;
    private final RedisUtils redisUtils;

    @PostMapping
    public CommonResponse insertPlayItem(Authentication authentication, @RequestBody @Valid YoutubePlayReqDto req){
        String accessToken = "Bearer "+redisUtils.getHashValue("googleAccessToken", authentication.getName());
        log.info("Youtube accessToken: "+accessToken);

        String playlistId = youtubePlayListService.selectPlayList(accessToken);
        log.info("Youtube playlistId: "+playlistId);

        String videoId = youtubePlayListService.selectPlayItem(req.getSearchName(),accessToken);
        log.info("Youtube videoId: "+videoId);

        boolean checkVideo = youtubePlayListService.selectPlayListItem(playlistId,videoId,accessToken);
        log.info("Youtube checkVideo: "+checkVideo);

        if (!checkVideo)
            youtubePlayListService.addPlayListItem(playlistId, videoId, accessToken);

        return new CommonResponse("200", "재생 목록 추가완료");
    }

}