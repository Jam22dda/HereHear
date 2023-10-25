package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
