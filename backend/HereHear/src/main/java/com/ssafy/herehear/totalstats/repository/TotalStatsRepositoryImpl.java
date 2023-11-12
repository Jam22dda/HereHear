package com.ssafy.herehear.totalstats.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.herehear.entity.QOccasion.occasion;
import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QMusicHistory.musicHistory;
import static com.ssafy.herehear.entity.QMusicOccasion.musicOccasion;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class TotalStatsRepositoryImpl implements TotalStatsRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> findByLikesSort(){
        return jpaQueryFactory.select(Projections.tuple(registeredMusic, likeMusic.registeredMusic.count().as("likeCount")))
                .from(likeMusic)
                .join(likeMusic.registeredMusic, registeredMusic)
                .where(registeredMusic.createTime.between(LocalDateTime.now().minusWeeks(1), LocalDateTime.now())
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .groupBy(likeMusic.registeredMusic.registeredMusicId)
                .orderBy(likeMusic.registeredMusic.count().desc())
                .limit(4)
                .fetch();
    }

    @Override
    public List<Tuple> findByTagsSort(){
        return jpaQueryFactory.select(Projections.tuple(musicOccasion.occasion.occasionName.as("tagName"), musicOccasion.occasion.occasionCode.count().as("tagCount")))
                .from(musicOccasion)
                .join(musicOccasion.registeredMusic, registeredMusic)
                .join(musicOccasion.occasion, occasion)
                .where(registeredMusic.createTime.between(LocalDateTime.now().minusWeeks(1), LocalDateTime.now())
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .groupBy(musicOccasion.occasion.occasionCode)
                .orderBy(musicOccasion.occasion.occasionCode.count().desc())
                .limit(5)
                .fetch();
    }

    @Override
    public RegisteredMusic findByTopHistoryMusic(){
        return jpaQueryFactory.select(musicHistory.registeredMusic)
                .from(musicHistory)
                .join(musicHistory.registeredMusic, registeredMusic)
                .where(registeredMusic.createTime.between(LocalDateTime.now().minusWeeks(1), LocalDateTime.now())
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .groupBy(musicHistory.registeredMusic.registeredMusicId)
                .orderBy(musicHistory.registeredMusic.registeredMusicId.count().desc())
                .limit(1)
                .fetchOne();
    }
}
