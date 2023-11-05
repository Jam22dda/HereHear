package com.ssafy.herehear.achievement.observer.aop;

import com.ssafy.herehear.achievement.observer.AchievementEventManager;
import com.ssafy.herehear.achievement.observer.events.EventType;
import com.ssafy.herehear.achievement.repository.RegisteredMusicRepository;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
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
    private final RegisteredMusicRepository registeredMusicRepository;

    @AfterReturning("execution(* com.ssafy.herehear.music.service.RegisteredMusicService.registerMusic(..))")
    public void afterMusicRegistrationAdvice() {
        Long memberId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());

        log.info("[음악 등록 후 AOP 실행] MUSIC_REGISTRATION 업적 체크 memberId: {}", memberId);

        achievementEventManager.notify(EventType.MUSIC_REGISTRATION, memberId);
    }

    @AfterReturning("execution(* com.ssafy.herehear.like.service.LikeMusicService.registerlikeMusic(..))")
    public void afterRegisterLikeMusicAdvice(JoinPoint joinPoint) {
        Long registeredMusicId = (Long) joinPoint.getArgs()[1];

        log.info("[좋아요 등록 후 AOP 실행] LIKE_COUNT 업적 체크 registeredMusicId: {}", registeredMusicId);

        RegisteredMusic registeredMusic = registeredMusicRepository.findByRegisteredMusicId(registeredMusicId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC));

        achievementEventManager.notify(EventType.LIKE_COUNT, registeredMusic.getMember().getMemberId());
        achievementEventManager.notify(EventType.TOTAL_LIKE, registeredMusicId);
    }

    @AfterReturning("execution(* com.ssafy.herehear.member.service.MemberService.follow(..))")
    public void afterFollowAdvice(JoinPoint joinPoint) {
        Long memberId = (Long) joinPoint.getArgs()[1];

        log.info("[팔로우 등록 후 AOP 실행] FOLLOWER_COUNT 업적 체크 memberId: {}", memberId);

        achievementEventManager.notify(EventType.FOLLOWER_COUNT, memberId);
    }

}
