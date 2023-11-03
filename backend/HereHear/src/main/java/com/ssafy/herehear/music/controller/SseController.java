package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.music.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        SseEmitter sseEmitter = sseService.subscribe(memberId);
        sseService.sendToClient(Long.parseLong(authentication.getName()), new CommonResponse("200", "SSE 구독완료"));

        return sseEmitter;
    }

}
