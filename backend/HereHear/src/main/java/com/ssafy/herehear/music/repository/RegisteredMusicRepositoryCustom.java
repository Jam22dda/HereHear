package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;

public interface RegisteredMusicRepositoryCustom {

    List<RegisteredMusic> findByMyRegisterMusics(long memberId);

}
