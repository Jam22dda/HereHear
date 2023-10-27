package com.ssafy.herehear.achievement.observer;

import com.ssafy.herehear.achievement.observer.events.EventType;
import com.ssafy.herehear.achievement.observer.events.FollowerCountEvent;
import com.ssafy.herehear.achievement.observer.events.LikeCountEvent;
import com.ssafy.herehear.achievement.observer.events.MusicRegistrationEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AchievementEventManager {

    Map<EventType, List<EventListener>> listeners = new HashMap<>();

    // Bean 생성 후 EventType 에 정의된 모든 이벤트를 등록
    @PostConstruct
    public void init() {
        for (EventType eventType : EventType.values()) {
            listeners.put(eventType, new ArrayList<>());
        }

        listeners.get(EventType.MUSIC_REGISTRATION).add(new MusicRegistrationEvent());  // 음악 등록 이벤트
        listeners.get(EventType.LIKE_COUNT).add(new LikeCountEvent());                  // 좋아요 이벤트
        listeners.get(EventType.FOLLOWER_COUNT).add(new FollowerCountEvent());          // 팔로우 이벤트
    }

    public void subscribe(EventType eventType, EventListener listener) {
        List<EventListener> events = listeners.get(eventType);
        events.add(listener);
    }

    public void unsubscribe(EventType eventType, EventListener listener) {
        List<EventListener> events = listeners.get(eventType);
        events.remove(listener);
    }

    public void notify(EventType eventType, Long memberId) {
        List<EventListener> events = listeners.get(eventType);
        for (EventListener listener : events) {
            listener.update(eventType, memberId);
        }
    }
}
