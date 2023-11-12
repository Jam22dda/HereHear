package com.ssafy.herehear.statistics.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component("musicLikeMusicRepositoryImpl")
@RequiredArgsConstructor
public class LikeMusicRepositoryImpl implements LikeMusicRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer findLikeCountByMemberId(long memberId) {
        return jpaQueryFactory.select(likeMusic.count())
                .from(likeMusic)
                .join(likeMusic.registeredMusic, registeredMusic)
                .where(registeredMusic.member.memberId.eq(memberId)
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .fetchFirst().intValue();
    }
}
