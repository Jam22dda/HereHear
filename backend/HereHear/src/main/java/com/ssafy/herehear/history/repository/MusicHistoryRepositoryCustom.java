package com.ssafy.herehear.history.repository;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;
import java.util.Optional;

public interface MusicHistoryRepositoryCustom {

    Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId);

    List<RegisteredMusic> findByMusicHistorys(long memberId);

    Optional<LikeMusic> findByRegisteredMusicLike(long memberId, long registeredMusicId);

}
