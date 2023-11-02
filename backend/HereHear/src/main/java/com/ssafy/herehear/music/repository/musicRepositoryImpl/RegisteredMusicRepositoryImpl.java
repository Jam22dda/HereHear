package com.ssafy.herehear.music.repository.musicRepositoryImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QMusicOccasion.musicOccasion;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class RegisteredMusicRepositoryImpl implements RegisteredMusicRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(registeredMusic)
                .where(
                        registeredMusic.registeredMusicId.eq(registeredMusicId)
                                .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<LikeMusic> findByRegisteredMusicLike(long memberId, long registeredMusicId) {
        return Optional.ofNullable(jpaQueryFactory.select(likeMusic)
                .from(likeMusic)
                .where(likeMusic.registeredMusic.registeredMusicId.eq(registeredMusicId)
                        .and(likeMusic.member.memberId.eq(memberId))
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .fetchOne()
        );
    }

    @Override
    public List<String> findByOccasionName(long registeredMusicId) {
        return jpaQueryFactory.select(musicOccasion.occasion.occasionName)
                .from(musicOccasion)
                .where(musicOccasion.registeredMusic.registeredMusicId.eq(registeredMusicId))
                .fetch();
    }

    @Override
    public List<RegisteredMusic> findByRegisterMusics() {
        return jpaQueryFactory.selectFrom(registeredMusic)
                .where(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                .fetch();
    }

    @Override
    public Optional<RegisteredMusic> findByMyRegisterMusic(long memberId, long registeredMusicId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(registeredMusic)
                .where(
                        registeredMusic.member.memberId.eq(memberId)
                                .and(registeredMusic.registeredMusicId.eq(registeredMusicId))
                                .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                )
                .fetchOne()
        );
    }

    @Override
    public List<RegisteredMusic> findByMyRegisterMusics(long memberId) {
        return jpaQueryFactory.selectFrom(registeredMusic)
                .where(registeredMusic.member.memberId.eq(memberId)
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                )
                .fetch();
    }

}
