package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.RegisteredMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AchievementRegisteredMusicRepository extends JpaRepository<RegisteredMusic, Long> {

    @Query("select count(rm) from RegisteredMusic rm where rm.member.memberId = :memberId and rm.isDeleted = false")
    long countByMemberId(Long memberId);

}
