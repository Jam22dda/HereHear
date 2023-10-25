package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.MemberAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberAchievementRepository extends JpaRepository<MemberAchievement, Long> {

    @Query("select ma from MemberAchievement ma join fetch ma.achievement where ma.member.memberId = :memberId")
    List<MemberAchievement> finaByMemberId(Long memberId);

}
