package com.ssafy.herehear.achievement.observer.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AchievementType {
    // 음악 등록
    씨앗_DJ, 새싹_DJ, 잎새_DJ, 열매_DJ, 나무_DJ,

    // 좋아요 수
    콩닥콩닥, 두근두근, 하트비트, Here_Hear,

    // 팔로워 수
    스타10, 스타50, 스타100, 스타500;

    private long count;
}
