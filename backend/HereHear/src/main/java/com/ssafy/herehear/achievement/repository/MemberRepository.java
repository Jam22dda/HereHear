package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("achievementMemberRepository")
public interface MemberRepository extends JpaRepository<Member, Long> {
}
