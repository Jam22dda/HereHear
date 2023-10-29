package com.ssafy.herehear.achievement.repository;

import com.ssafy.herehear.entity.LikeMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikeMusicRepository extends JpaRepository<LikeMusic, Long> {

    @Query("select count(l) from LikeMusic l join fetch l.registeredMusic rm where rm.member.memberId = :memberId")
    long countByMemberRegisteredMusic(Long memberId);

}
