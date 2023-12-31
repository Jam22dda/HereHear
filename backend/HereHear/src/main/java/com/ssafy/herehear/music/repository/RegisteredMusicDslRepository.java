package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;
import java.util.Optional;

public interface RegisteredMusicDslRepository {

    Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId);

    Optional<LikeMusic> findByRegisteredMusicLike(long memberId, long registeredMusicId);

    List<String> findByOccasionName(long registeredMusicId);

    List<RegisteredMusic> findByRegisterMusics(int minutesBefore, int minutesAfter);

    Optional<RegisteredMusic> findByMyRegisterMusic(long memberId, long registeredMusicId);

    List<RegisteredMusic> findByMyRegisterMusics(long memberId);

}
