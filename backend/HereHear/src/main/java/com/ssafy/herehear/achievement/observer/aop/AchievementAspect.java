package com.ssafy.herehear.achievement.observer.aop;

import com.ssafy.herehear.achievement.observer.AchievementEventManager;
import com.ssafy.herehear.achievement.observer.events.EventType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AchievementAspect {

    private final AchievementEventManager achievementEventManager;

    @AfterReturning("execution(* com.ssafy.herehear.music.service.RegisteredMusicService.registerMusic(..))")
    public void afterMusicRegistration() {
        Long memberId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        log.info("[음악 등록 후 AOP 실행] MUSIC_REGISTRATION 업적 체크 memberId: {}", memberId);

        achievementEventManager.notify(EventType.MUSIC_REGISTRATION, memberId);
    }

}
