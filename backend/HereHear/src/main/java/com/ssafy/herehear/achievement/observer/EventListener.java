package com.ssafy.herehear.achievement.observer;

import com.ssafy.herehear.achievement.observer.events.EventType;

public interface EventListener {
    void update(EventType eventType, Long memberId);
}
