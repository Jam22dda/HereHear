package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.type.AchievementCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    List<Achievement> findByCategory(AchievementCategoryType achievementCategoryType);

}
