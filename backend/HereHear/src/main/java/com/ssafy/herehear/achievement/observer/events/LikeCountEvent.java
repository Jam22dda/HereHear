package com.ssafy.herehear.achievement.observer.events;

import com.ssafy.herehear.achievement.observer.EventListener;
import com.ssafy.herehear.achievement.repository.*;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberAchievement;
import com.ssafy.herehear.entity.MemberAchievementId;
import com.ssafy.herehear.entity.type.AchievementCategoryType;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeCountEvent implements EventListener {

    private final MemberRepository memberRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final LikeMusicRepository likeMusicRepository;
    private final AchievementRepository achievementRepository;
    private final RegisteredMusicRepository registeredMusicRepository;

    @Override
    public void update(EventType eventType, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        List<Achievement> memberAchievementList = memberAchievementRepository.findByMemberId(memberId).stream()
                .map(MemberAchievement::getAchievement).toList();

        long allLikeCount = likeMusicRepository.countByMemberRegisteredMusic(memberId);

        checkAchievementCondition(member, allLikeCount, memberAchievementList);
    }

    // 좋아요 달성조건 체크
    private void checkAchievementCondition(Member member, long likeCount, List<Achievement> memberAchievementList) {
        List<Achievement> achievementList = achievementRepository.findByCategory(AchievementCategoryType.LIKE);

        for (Achievement achievement : achievementList) {
            if (likeCount >= achievement.getCount() && !memberAchievementList.contains(achievement)) {
                memberAchievementRepository.save(MemberAchievement.builder()
                        .id(MemberAchievementId.builder().memberId(member.getMemberId()).achievementId(achievement.getAchievementId()).build())
                        .member(member)
                        .achievement(achievement)
                        .build());
            }
        }
    }
}
