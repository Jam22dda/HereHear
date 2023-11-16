package com.ssafy.herehear.totalstats.repository.totalstatsRepositoryImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.totalstats.repository.MonthlyStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.herehear.entity.QRegisteredMusic.registeredMusic;

@Component
@RequiredArgsConstructor
public class MonthlyStatisticsRepositoryImpl implements MonthlyStatisticsRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RegisteredMusic> findByMonthlyRegisteredMusics(){
        return jpaQueryFactory.select(registeredMusic)
                .from(registeredMusic)
                .where(registeredMusic.createTime.between(LocalDateTime.now().minusMonths(2), LocalDateTime.now().minusMonths(1))
                        .and(registeredMusic.isDeleted.isNull().or(registeredMusic.isDeleted.isFalse())))
                .fetch();
    }

}
