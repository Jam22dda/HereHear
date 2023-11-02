package com.ssafy.herehear.music.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


public interface SseService {

    SseEmitter subscribe(Long memberId);

    void notify(Long memberId, Object event);

    void sendToClient(Long memberId, Object data);

    SseEmitter createEmitter(Long memberId);
}