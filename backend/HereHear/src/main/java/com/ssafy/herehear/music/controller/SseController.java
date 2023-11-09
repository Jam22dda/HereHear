package com.ssafy.herehear.music.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import com.ssafy.herehear.music.service.SseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/music")
@RequiredArgsConstructor
@Slf4j
public class SseController {

    private final SseService sseService;

    @GetMapping(value = "/subscribe/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable Long memberId) {
        log.info("[SSE Subscribe] 구독 시도, memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));
        SseEmitter sseEmitter = sseService.subscribe(memberId);
        sseService.sendToClient(memberId, new CommonResponse("200", "SSE 구독완료"));
        log.info("[SSE Subscribe] 구독 완료, memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        return sseEmitter;
    }

}