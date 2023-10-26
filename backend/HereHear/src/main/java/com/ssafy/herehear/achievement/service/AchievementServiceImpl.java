package com.ssafy.herehear.achievement.service;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.mapper.AchievementMapper;
import com.ssafy.herehear.achievement.repository.AchievementRepository;
import com.ssafy.herehear.achievement.repository.MemberAchievementRepository;
import com.ssafy.herehear.entity.MemberAchievement;
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
        List<MemberAchievement> memberAchievementList = memberAchievementRepository.finaByMemberId(memberId);

        return memberAchievementList.stream()
                .map(item -> AchievementMapper.INSTANCE.toMemberAchievementDto(item.getAchievement(), memberId, TimeFormatUtil.formatTime(item.getClearTime())))
                .toList();
    }

    @Override
    public int getAchievementCount(Long memberId) {
        return memberAchievementRepository.countByMember_MemberId(memberId);
    }
}
