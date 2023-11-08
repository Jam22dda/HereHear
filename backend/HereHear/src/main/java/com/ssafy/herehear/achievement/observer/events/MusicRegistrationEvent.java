package com.ssafy.herehear.achievement.observer.events;

import com.ssafy.herehear.achievement.observer.EventListener;
import com.ssafy.herehear.achievement.repository.AchievementRepository;
import com.ssafy.herehear.achievement.repository.MemberAchievementRepository;
import com.ssafy.herehear.achievement.repository.MemberRepository;
import com.ssafy.herehear.achievement.repository.RegisteredMusicRepository;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberAchievement;
import com.ssafy.herehear.entity.MemberAchievementId;
import com.ssafy.herehear.entity.type.AchievementCategoryType;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicRegistrationEvent implements EventListener {

    private final MemberRepository memberRepository;
    private final AchievementRepository achievementRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final RegisteredMusicRepository registeredMusicRepository;

    @Override
    @Transactional
    public void update(EventType eventType, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        List<Achievement> memberAchievementList = memberAchievementRepository.findByMemberId(memberId).stream()
                .map(MemberAchievement::getAchievement).toList();

        long registeredMusicCount = registeredMusicRepository.countByMemberId(memberId);

        checkAchievementCondition(member, registeredMusicCount, memberAchievementList);
    }

    // 음악 등록 달성조건 체크
    private void checkAchievementCondition(Member member, long registeredMusicCount, List<Achievement> memberAchievementList) {
        List<Achievement> achievementList = achievementRepository.findByCategory(AchievementCategoryType.MUSIC_REGISTRATION);

        for (Achievement achievement : achievementList) {
            if (registeredMusicCount >= achievement.getCount() && !memberAchievementList.contains(achievement)) {
                memberAchievementRepository.save(MemberAchievement.builder()
                        .id(MemberAchievementId.builder().memberId(member.getMemberId()).achievementId(achievement.getAchievementId()).build())
                        .member(member)
                        .achievement(achievement)
                        .build());
            }
        }
    }
}
