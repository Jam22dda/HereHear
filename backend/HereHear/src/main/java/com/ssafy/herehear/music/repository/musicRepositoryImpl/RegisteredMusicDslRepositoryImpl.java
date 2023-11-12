package com.ssafy.herehear.music.repository.musicRepositoryImpl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.repository.RegisteredMusicDslRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QMusicOccasion.musicOccasion;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class RegisteredMusicDslRepositoryImpl implements RegisteredMusicDslRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Optional<RegisteredMusic> findByRegisterMusic(long registeredMusicId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(registeredMusic)
                .where(registeredMusic.registeredMusicId.eq(registeredMusicId)
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

    public List<RegisteredMusic> findByRegisterMusics(int minutesBefore, int minutesAfter) {

        // 현재 시간의 시간과 분을 추출
        LocalTime now = LocalTime.now();
        int currentMinutesOfDay = now.getHour() * 60 + now.getMinute();

        // 범위 계산
        int startMinutesOfDay = currentMinutesOfDay - minutesBefore;
        int endMinutesOfDay = currentMinutesOfDay + minutesAfter;

        JPAQuery<RegisteredMusic> query = new JPAQuery<>(entityManager);

        if (startMinutesOfDay < 0) {
            // 자정을 넘어가는 경우 (아침)
            query.select(registeredMusic)
                    .from(registeredMusic)
                    .where(registeredMusic.createTime.hour().multiply(60).add(registeredMusic.createTime.minute())
                            .goe(1440 + startMinutesOfDay)
                            .or(registeredMusic.createTime.hour().multiply(60).add(registeredMusic.createTime.minute())
                                    .loe(endMinutesOfDay)));
        } else if (endMinutesOfDay >= 1440) {
            // 자정을 넘어가는 경우 (밤)
            query.select(registeredMusic)
                    .from(registeredMusic)
                    .where(registeredMusic.createTime.hour().multiply(60).add(registeredMusic.createTime.minute())
                            .goe(startMinutesOfDay)
                            .or(registeredMusic.createTime.hour().multiply(60).add(registeredMusic.createTime.minute())
                                    .lt(endMinutesOfDay - 1440)));
        } else {
            // 일반적인 경우
            query.select(registeredMusic)
                    .from(registeredMusic)
                    .where(registeredMusic.createTime.hour().multiply(60).add(registeredMusic.createTime.minute())
                            .between(startMinutesOfDay, endMinutesOfDay));
        }

        return query.fetch();
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
