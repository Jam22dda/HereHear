package com.ssafy.herehear.achievement.observer.events;

import com.ssafy.herehear.achievement.observer.EventListener;
import com.ssafy.herehear.achievement.repository.*;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberAchievement;
import com.ssafy.herehear.entity.type.AchievementCategoryType;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FollowerCountEvent implements EventListener {

    private final MemberRepository memberRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final FollowRepository followRepository;
    private final AchievementRepository achievementRepository;

    @Override
    public void update(EventType eventType, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        List<Achievement> memberAchievementList = memberAchievementRepository.findByMemberId(memberId).stream()
                .map(MemberAchievement::getAchievement).toList();

        long followerCount = followRepository.countByFollowMemberId(memberId);

        checkAchievementCondition(member, followerCount, memberAchievementList);
    }

    // 팔로우 달성조건 체크
    private void checkAchievementCondition(Member member, long followerCount, List<Achievement> memberAchievementList) {
        List<Achievement> achievementList = achievementRepository.findByCategory(AchievementCategoryType.FOLLOWER);

        for (Achievement achievement : achievementList) {
            if (followerCount >= achievement.getCount() && !memberAchievementList.contains(achievement)) {
                memberAchievementRepository.save(MemberAchievement.builder()
                        .member(member)
                        .achievement(achievement)
                        .build());
            }
        }
    }
}
