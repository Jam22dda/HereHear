package com.ssafy.herehear.admin.repository;

import com.ssafy.herehear.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAchievementRepository extends JpaRepository<Achievement, Long> {
}
