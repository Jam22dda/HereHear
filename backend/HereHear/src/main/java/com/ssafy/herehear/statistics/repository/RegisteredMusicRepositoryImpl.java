package com.ssafy.herehear.statistics.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.statistics.dto.response.TagResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ssafy.herehear.entity.QMusicHistory.musicHistory;
import static com.ssafy.herehear.entity.QMusicOccasion.musicOccasion;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component("statisticsRegisteredMusicRepositoryImpl")
@RequiredArgsConstructor
public class RegisteredMusicRepositoryImpl implements RegisteredMusicRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<TagResDto> findAllTagsCountByMemberId(Long memberId) {
        return
                jpaQueryFactory.select(Projections.fields
                                (TagResDto.class, musicOccasion.occasion.occasionName, musicOccasion.occasion.count().as("occasionCount")))
                            .from(musicOccasion)
                            .join(musicOccasion.registeredMusic, registeredMusic)
                            .where(registeredMusic.member.memberId.eq(memberId)
                                    .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                            .groupBy(musicOccasion.occasion.occasionCode)
                            .orderBy(musicOccasion.occasion.count().desc())
                            .fetch();
    }

    @Override
    public List<Integer> findAllTimeByMemberId(Long memberId) {
        return jpaQueryFactory.select(registeredMusic.createTime.hour())
                .from(registeredMusic)
                .where(registeredMusic.member.memberId.eq(memberId)
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .orderBy(registeredMusic.createTime.hour().desc())
                .fetch();
    }

    @Override
    public List<Integer> findAllTimeHistoryByMemberId(Long memberId) {
        return jpaQueryFactory.select(musicHistory.createTime.hour())
                .from(musicHistory)
                .join(musicHistory.registeredMusic, registeredMusic)
                .where(musicHistory.member.memberId.eq(memberId)
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .orderBy(musicHistory.createTime.hour().desc())
                .fetch();
    }
}
