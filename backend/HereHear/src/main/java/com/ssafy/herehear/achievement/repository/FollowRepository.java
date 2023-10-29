package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("achievementFollowRepository")
public interface FollowRepository extends JpaRepository<Follow, Long> {

    long countByFollowMemberId(Long memberId);

}
