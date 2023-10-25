package com.ssafy.herehear.achievement.service;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.mapper.AchievementMapper;
import com.ssafy.herehear.achievement.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    @Transactional
    public List<AchievementDto> getAchievementList() {
        return achievementRepository.findAll()
                .stream().map(AchievementMapper.INSTANCE::toAchievementDto)
                .toList();
    }
}
