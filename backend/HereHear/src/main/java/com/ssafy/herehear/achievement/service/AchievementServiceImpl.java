package com.ssafy.herehear.achievement.service;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.EquipAchievementDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.mapper.AchievementMapper;
import com.ssafy.herehear.achievement.repository.*;
import com.ssafy.herehear.entity.BorderCode;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberAchievement;
import com.ssafy.herehear.entity.TitleCode;
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
    private final BorderCodeRepository borderCodeRepository;
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
        List<MemberAchievement> memberAchievementList = memberAchievementRepository.finaByMemberId(memberId);

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

        // 0이면 장착 해제
        Long zero = 0L;
        if (zero.equals(equipAchievementDto.getBorderCode())) {
            member.updateBorderCode(null);
            equipAchievementDto.setBorderCode(null);
        }
        if (zero.equals(equipAchievementDto.getTitleCode())) {
            member.updateTitleCode(null);
            equipAchievementDto.setTitleCode(null);
        }

        // 그 외의 숫자면 장착, null 이면 기존 장착된 것 그대로
        if (equipAchievementDto.getBorderCode() != null && equipAchievementDto.getTitleCode() != null) {
            equipTitleAndBorder(member, equipAchievementDto);
        } else if (equipAchievementDto.getBorderCode() != null) {
            equipBorder(member, equipAchievementDto);
        } else if (equipAchievementDto.getTitleCode() != null) {
            equipTitle(member, equipAchievementDto);
        }

        memberRepository.save(member);
    }

    private void equipTitleAndBorder(Member member, EquipAchievementDto equipAchievementDto) {
        TitleCode titleCode = titleCodeRepository.findById(equipAchievementDto.getTitleCode())
                .orElseThrow(() -> new CustomException(ExceptionStatus.TITLE_CODE_NOT_FOUND));

        BorderCode borderCode = borderCodeRepository.findById(equipAchievementDto.getBorderCode())
                .orElseThrow(() -> new CustomException(ExceptionStatus.BORDER_CODE_NOT_FOUND));

        member.updateTitleCode(titleCode);
        member.updateBorderCode(borderCode);
    }

    private void equipTitle(Member member, EquipAchievementDto equipAchievementDto) {
        TitleCode titleCode = titleCodeRepository.findById(equipAchievementDto.getTitleCode())
                .orElseThrow(() -> new CustomException(ExceptionStatus.TITLE_CODE_NOT_FOUND));

        member.updateTitleCode(titleCode);
    }

    private void equipBorder(Member member, EquipAchievementDto equipAchievementDto) {
        BorderCode borderCode = borderCodeRepository.findById(equipAchievementDto.getBorderCode())
                .orElseThrow(() -> new CustomException(ExceptionStatus.BORDER_CODE_NOT_FOUND));

        member.updateBorderCode(borderCode);
    }

}
