package com.ssafy.herehear.music.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


public interface SseService {

    void notifyAllMembers(Object event);

    SseEmitter subscribe(Long memberId, Object data);

}