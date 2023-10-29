package com.ssafy.herehear.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ssafy.herehear.entity.QLikeMusic.likeMusic;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class LikeMusicRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;

    public List<RegisteredMusic> findByLikeMusics(long memberId) {
        return jpaQueryFactory.select(registeredMusic)
                .from(likeMusic)
                .join(likeMusic.registeredMusic, registeredMusic)
                .where(likeMusic.member.memberId.eq(memberId)
                        .and(registeredMusic.isDeleted.isNull()))
                .orderBy(likeMusic.createTime.desc())
                .fetch();
    }
}
