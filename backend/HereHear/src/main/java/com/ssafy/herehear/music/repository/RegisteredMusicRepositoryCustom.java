package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;

public interface RegisteredMusicRepositoryCustom {

    //유저의 등록 음악 목록
    List<RegisteredMusic> findByMyRegisterMusics(long memberId);

}
