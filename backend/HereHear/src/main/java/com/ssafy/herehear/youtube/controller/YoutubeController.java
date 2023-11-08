package com.ssafy.herehear.youtube.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.youtube.dto.YoutubePlayReqDto;
import com.ssafy.herehear.youtube.service.AddPlayItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/youtube")
@RequiredArgsConstructor
public class YoutubeController {

    private final AddPlayItemService addPlayItemService;

    @PostMapping
    public CommonResponse insertPlayItem(@RequestBody YoutubePlayReqDto req){
        String listId = addPlayItemService.selectPlayList();
        if(listId.isEmpty())
            addPlayItemService.insertPlayList();
//        addPlayItemService.addPlayItem(req.getVideoId());
        return new CommonResponse("200", "재생 목록 추가완료");
    }

}