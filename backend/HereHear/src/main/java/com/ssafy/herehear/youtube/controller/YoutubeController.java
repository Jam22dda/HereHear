package com.ssafy.herehear.youtube.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.youtube.dto.YoutubePlayReqDto;
import com.ssafy.herehear.youtube.service.YoutubePlayListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor
public class YoutubeController {

    private final YoutubePlayListService youtubePlayListService;

    @PostMapping
    public CommonResponse insertPlayItem(@RequestBody YoutubePlayReqDto req){
        String playlistId = youtubePlayListService.selectPlayList();
        log.info("Youtube playlistId: "+playlistId);

        String videoId = youtubePlayListService.selectPlayListItem(req.getSearchName());
        log.info("Youtube videoId: "+videoId);

        youtubePlayListService.addPlayListItem(playlistId, videoId);
        return new CommonResponse("200", "재생 목록 추가완료");
    }

}