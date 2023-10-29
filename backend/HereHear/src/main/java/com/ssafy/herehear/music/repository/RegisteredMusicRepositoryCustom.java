package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;
import java.util.Optional;

public interface RegisteredMusicRepositoryCustom {

    Optional<RegisteredMusic> findByRegisterMusicDetails(long registeredMusicId);

//    Long countLikesByRegisteredMusic(long registeredMusicId);

    List<Occasion> findByOccasion(long registeredMusicId);

    Optional<LikeMusic> findByRegisteredMusicLike(long memberId, long registeredMusicId);

    List<RegisteredMusic> findByRegisterMusics();

//    List<RegisteredMusic> findByMyRegisterMusics(long memberId);
//
//    Optional<RegisteredMusic> findByMyRegisterMusic(long memberId, long registeredMusicId);

}
