package com.ssafy.herehear.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class LikeMusicRepositoryImpl implements LikeMusicRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public List<RegisteredMusic> findByLikeMusics(long memberId) {
        return jpaQueryFactory.select(registeredMusic)
                .from(likeMusic)
                .join(likeMusic.registeredMusic, registeredMusic)
                .where(likeMusic.member.memberId.eq(memberId)
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .orderBy(likeMusic.createTime.desc())
                .fetch();
    }

    @Override
    public Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId) {
        return Optional.ofNullable( jpaQueryFactory.selectFrom(registeredMusic)
                .where(
                        registeredMusic.registeredMusicId.eq(registeredMusicId)
                                .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<LikeMusic> findByRegisteredMusicLike(long memberId, long registeredMusicId) {
        return Optional.ofNullable( jpaQueryFactory.select(likeMusic)
                .from(likeMusic)
                .where(likeMusic.registeredMusic.registeredMusicId.eq(registeredMusicId)
                        .and(likeMusic.member.memberId.eq(memberId)))
                .fetchOne()
        );
    }

}
