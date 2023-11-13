package com.ssafy.herehear.history.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;
import java.util.Optional;

public interface MusicHistoryDslRepository {

    Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId);

    List<RegisteredMusic> findByMusicHistorys(long memberId);

}
