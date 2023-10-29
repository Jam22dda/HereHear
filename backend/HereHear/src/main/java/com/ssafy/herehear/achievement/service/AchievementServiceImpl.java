package com.ssafy.herehear.achievement.service;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.EquipAchievementDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.mapper.AchievementMapper;
import com.ssafy.herehear.achievement.repository.*;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;
    private final MemberAchievementRepository memberAchievementRepository;
    private final BadgeCodeRepository badgeCodeRepository;
    private final TitleCodeRepository titleCodeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public List<AchievementDto> getAchievementList() {
        return achievementRepository.findAll()
                .stream().map(AchievementMapper.INSTANCE::toAchievementDto)
                .toList();
    }

    @Override
    @Transactional
    public List<MemberAchievementDto> getMyAchievementList(Long memberId) {
        List<MemberAchievement> memberAchievementList = memberAchievementRepository.findByMemberId(memberId);

        return memberAchievementList.stream()
                .map(item -> AchievementMapper.INSTANCE.toMemberAchievementDto(item.getAchievement(), memberId, TimeFormatUtil.formatTime(item.getClearTime())))
                .toList();
    }

    @Override
    public int getAchievementCount(Long memberId) {
        return memberAchievementRepository.countByMember_MemberId(memberId);
    }

    @Override
    @Transactional
    public void equipAchievement(Long memberId, EquipAchievementDto equipAchievementDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        Long zero = 0L;
        if (zero.equals(equipAchievementDto.getAchievementId())) {
            member.updateAchievement(null);
        } else {
            Achievement achievement = achievementRepository.findById(equipAchievementDto.getAchievementId())
                    .orElseThrow(() -> new CustomException(ExceptionStatus.ACHIEVEMENT_NOT_FOUND));
            member.updateAchievement(achievement);
        }

        memberRepository.save(member);
    }

}
