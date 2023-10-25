package com.ssafy.herehear.music.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import java.util.Collections;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RegisteredMusicRepositoryImpl implements RegisteredMusicRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RegisteredMusic> findByMyRegisterMusics(long memberId) {
        return Collections.emptyList();
    }
}
