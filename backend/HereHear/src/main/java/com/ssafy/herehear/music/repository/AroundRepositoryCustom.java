package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;

public interface AroundRepositoryCustom{

    List<RegisteredMusic> findByRegisterMusics();

    List<String> findByOccasionName(long registeredMusicId);

    List<RegisteredMusic> findByAroundSearchMusics(String keyword, List<Long> occasions);

}
