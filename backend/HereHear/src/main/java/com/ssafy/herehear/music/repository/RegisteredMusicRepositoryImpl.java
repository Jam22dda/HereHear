package com.ssafy.herehear.music.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;
import static com.ssafy.herehear.entity.QMember.member;
import static com.ssafy.herehear.entity.QMemberReadList.memberReadList;

@RequiredArgsConstructor
public class RegisteredMusicRepositoryImpl implements RegisteredMusicRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RegisteredMusic> findByMyRegisterMusics(long memberId) {
        return jpaQueryFactory
                .select(registeredMusic)
                .from(registeredMusic)
                .innerJoin(memberReadList.member, member)
                .where(member.memberId.eq(memberId))
                .fetch();

    }
}
