package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;
import java.util.Optional;

public interface RegisteredMusicRepositoryCustom {

    List<RegisteredMusic> findByRegisterMusics();

    Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId);

    List<RegisteredMusic> findByMyRegisterMusics(long memberId);

    Optional<RegisteredMusic> findByMyRegisterMusic(long memberId, long registeredMusicId);

}
