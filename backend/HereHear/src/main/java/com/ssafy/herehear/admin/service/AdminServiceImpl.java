package com.ssafy.herehear.admin.service;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.admin.mapper.CreateAchievementMapper;
import com.ssafy.herehear.admin.repository.AdminAchievementRepository;
import com.ssafy.herehear.admin.repository.AdminBorderCodeRepository;
import com.ssafy.herehear.admin.repository.AdminTitleCodeRepository;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.BadgeCode;
import com.ssafy.herehear.entity.TitleCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminBorderCodeRepository adminBorderCodeRepository;
    private final AdminTitleCodeRepository adminTitleCodeRepository;
    private final AdminAchievementRepository adminAchievementRepository;

    @Override
    public void createAchievement(CreateAchievementDto createAchievementDto) {
        TitleCode titleCode = CreateAchievementMapper.INSTANCE.toTitleCodeEntity(createAchievementDto);
        BadgeCode badgeCode = CreateAchievementMapper.INSTANCE.toBadgeCodeEntity(createAchievementDto);
        Achievement achievement = CreateAchievementMapper.INSTANCE.toAchievementEntity(createAchievementDto, titleCode, badgeCode);

        adminTitleCodeRepository.save(titleCode);
        adminBorderCodeRepository.save(badgeCode);
        adminAchievementRepository.save(achievement);
    }
}
