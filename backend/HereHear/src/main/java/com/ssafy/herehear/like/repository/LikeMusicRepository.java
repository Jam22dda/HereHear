package com.ssafy.herehear.like.repository;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.MemberMusicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeMusicRepository extends JpaRepository<LikeMusic, MemberMusicId> {
}
