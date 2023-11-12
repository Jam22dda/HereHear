package com.ssafy.herehear.like.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;
import java.util.Optional;

public interface LikeMusicDslRepository {

    List<RegisteredMusic> findByLikeMusics(long memberId);

    Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId);

}
