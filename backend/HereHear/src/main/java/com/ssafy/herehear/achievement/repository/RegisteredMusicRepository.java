package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.RegisteredMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("achievementRegisteredMusicRepository")
public interface RegisteredMusicRepository extends JpaRepository<RegisteredMusic, Long> {

    @Query("select count(rm) from RegisteredMusic rm where rm.member.memberId = :memberId and rm.isDeleted = null")
    long countByMemberId(@Param("memberId") Long memberId);

    Optional<RegisteredMusic> findByRegisteredMusicId(Long registeredMusicId);

}
