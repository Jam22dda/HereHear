package com.ssafy.herehear.statistics.repository;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.MemberMusicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("statisiticsLikeMusicRepository")
public interface LikeMusicRepository extends JpaRepository<LikeMusic, MemberMusicId> {

}
