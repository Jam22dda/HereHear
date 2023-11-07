package com.ssafy.herehear.youtube.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.youtube.dto.YoutubePlayReqDto;
import com.ssafy.herehear.youtube.service.AddPlayItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor
public class YoutubeController {

    private final AddPlayItemService addPlayItemService;

    @PostMapping
    public CommonResponse insertPlayItem(@RequestBody YoutubePlayReqDto req) throws GeneralSecurityException, IOException {
        addPlayItemService.selectPlayList();
        addPlayItemService.insertPlayList();
        addPlayItemService.addPlayItem(req.getVideoId());
        return new CommonResponse("200", "재생 목록 추가완료");
    }

}