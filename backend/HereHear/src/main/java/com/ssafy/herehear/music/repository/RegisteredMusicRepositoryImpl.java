package com.ssafy.herehear.music.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//import static com.ssafy.herehear.entity.QMemberReadList.memberReadList;
//import static com.ssafy.herehear.entity.QMusic.music;
//import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class RegisteredMusicRepositoryImpl implements RegisteredMusicRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
//
//    @Override
//    public List<RegisteredMusic> findByRegisterMusics() {//음악 전체 조회
//        return jpaQueryFactory.selectFrom(registeredMusic)
//                .innerJoin(registeredMusic.music, music).fetchJoin()
//                .where(registeredMusic.isDeleted.isNull())
//                .fetch();
//    }
//
//    @Override
//    public Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId) {//음악 상세 조회
//        return Optional.ofNullable( jpaQueryFactory.selectFrom(registeredMusic)
//                .innerJoin(registeredMusic.music, music).fetchJoin()
//                .where(
//                        registeredMusic.registeredMusicId.eq(registeredMusicId)
//                                .and(registeredMusic.isDeleted.isNull())
//                )
//                .fetchOne()
//        );
//    }
//
//    @Override
//    public List<RegisteredMusic> findByMyRegisterMusics(long memberId) {//내 음악 전체 조회
//        return jpaQueryFactory.select(memberReadList.registeredMusic)
//                .from(memberReadList)
//                .where(memberReadList.member.memberId.eq(memberId)
//                        .and(memberReadList.registeredMusic.isDeleted.isNull()))
//                .fetch();
//    }
//
//    @Override
//    public Optional<RegisteredMusic> findByMyRegisterMusic(long memberId, long registeredMusicId) {//내 음악 상세 조회(update)
//        return Optional.ofNullable( jpaQueryFactory.select(memberReadList.registeredMusic)
//                .from(memberReadList)
//                .where(memberReadList.member.memberId.eq(memberId)
//                        .and(memberReadList.registeredMusic.registeredMusicId.eq(registeredMusicId)))
//                .fetchOne()
//        );
//    }
}
