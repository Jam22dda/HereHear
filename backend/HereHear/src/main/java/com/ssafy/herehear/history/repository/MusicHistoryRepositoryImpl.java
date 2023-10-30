package com.ssafy.herehear.history.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ssafy.herehear.entity.QMusicHistory.musicHistory;
import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class MusicHistoryRepositoryImpl {

    private final JPAQueryFactory jpaQueryFactory;

    public List<RegisteredMusic> findByMusicHistorys(long memberId) {
        return jpaQueryFactory.select(registeredMusic)
                .from(musicHistory)
                .join(musicHistory.registeredMusic, registeredMusic)
                .where(musicHistory.member.memberId.eq(memberId))
                .orderBy(musicHistory.createTime.desc())
                .fetch();
    }
}