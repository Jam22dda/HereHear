package com.ssafy.herehear.achievement.observer.events;

import com.ssafy.herehear.achievement.observer.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MusicRegistrationEvent implements EventListener {
    @Override
    public void update(EventType eventType, Long memberId) {

    }
}
