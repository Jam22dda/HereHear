package com.ssafy.herehear.music.repository.musicRepositoryImpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ssafy.herehear.entity.QMusicOccasion.musicOccasion;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class AroundRepositoryImpl implements AroundRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RegisteredMusic> findByRegisterMusics() {
        return jpaQueryFactory.selectFrom(registeredMusic)
                .where(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                .fetch();
    }

    @Override
    public List<String> findByOccasionName(long registeredMusicId) {
        return jpaQueryFactory.select(musicOccasion.occasion.occasionName)
                .from(musicOccasion)
                .where(musicOccasion.registeredMusic.registeredMusicId.eq(registeredMusicId))
                .fetch();
    }

    public List<RegisteredMusic> findByAroundSearchMusics(String keyword, List<Long> occasions) {
        BooleanBuilder builder = new BooleanBuilder();

        // occasions 배열이 비어있지 않으면 필터를 생성
        if (!occasions.isEmpty()) {
            builder.and(registeredMusic.registeredMusicId.in(
                    JPAExpressions
                            .select(musicOccasion.registeredMusic.registeredMusicId)
                            .from(musicOccasion)
                            .where(musicOccasion.occasion.occasionCode.in(occasions))
            ));
        }

        return jpaQueryFactory.selectFrom(registeredMusic)
                .where(
                        registeredMusic.subject.contains(keyword).or(registeredMusic.singer.contains(keyword))
                                .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse()))
                                .and(builder)
                )
                .fetch();
    }

}
