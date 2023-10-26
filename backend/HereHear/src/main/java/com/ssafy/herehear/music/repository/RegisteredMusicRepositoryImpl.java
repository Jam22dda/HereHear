package com.ssafy.herehear.music.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.herehear.entity.QMusic.music;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@RequiredArgsConstructor
public class RegisteredMusicRepositoryImpl implements RegisteredMusicRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RegisteredMusic> findByMyRegisterMusics(long memberId) {
        return jpaQueryFactory.selectFrom(registeredMusic)
                .innerJoin(registeredMusic.music, music)
                .where(registeredMusic.music.musicId.eq(memberId))
                .fetch();
    }
}
