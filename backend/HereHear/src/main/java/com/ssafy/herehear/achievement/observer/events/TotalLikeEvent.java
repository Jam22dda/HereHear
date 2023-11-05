package com.ssafy.herehear.achievement.observer.events;

import com.ssafy.herehear.achievement.observer.EventListener;
import com.ssafy.herehear.achievement.repository.AchievementRepository;
import com.ssafy.herehear.achievement.repository.LikeMusicRepository;
import com.ssafy.herehear.achievement.repository.MemberAchievementRepository;
import com.ssafy.herehear.achievement.repository.RegisteredMusicRepository;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.entity.type.AchievementCategoryType;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TotalLikeEvent implements EventListener {

    private final RegisteredMusicRepository registeredMusicRepository;
    private final LikeMusicRepository likeMusicRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final AchievementRepository achievementRepository;

    @Override
    public void update(EventType eventType, Long registeredMusicId) {
        long likeCount = likeMusicRepository.countByRegisteredMusic_RegisteredMusicId(registeredMusicId);

        RegisteredMusic registeredMusic = registeredMusicRepository.findByRegisteredMusicId(registeredMusicId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC));

        Member member = MemberUtil.findMember(registeredMusic.getMember().getMemberId());

        List<Achievement> memberAchievementList = memberAchievementRepository.findByMemberId(member.getMemberId()).stream()
                .map(MemberAchievement::getAchievement).toList();

        checkAchievementCondition(member, likeCount, memberAchievementList);
    }

    private void checkAchievementCondition(Member member, long likeCount, List<Achievement> memberAchievementList) {
        List<Achievement> achievementList = achievementRepository.findByCategory(AchievementCategoryType.TOTAL_LIKE);

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
