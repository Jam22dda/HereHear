package com.ssafy.herehear.member.repository;

import com.ssafy.herehear.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f join fetch f.member m where f.followMemberId = :memberId")
    List<Follow> findByFollowerMemberId(Long memberId);

    @Query("select f from Follow f where f.member.memberId = :memberId")
    List<Follow> findByFollowingMemberId(Long memberId);

    @Query("select f from Follow f where f.member.memberId = :memberId and f.followMemberId = :followingMemberId")
    Optional<Follow> findByMemberIdAndFollowMemberId(Long memberId, Long followingMemberId);

}
