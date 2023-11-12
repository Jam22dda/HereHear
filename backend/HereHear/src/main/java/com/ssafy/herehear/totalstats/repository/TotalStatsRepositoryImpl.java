package com.ssafy.herehear.totalstats.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class TotalStatsRepositoryImpl implements TotalStatsRepository{

    private final JPAQueryFactory jpaQueryFactory;

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

}
