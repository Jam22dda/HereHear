package com.ssafy.herehear.totalstats.repository;

import com.querydsl.core.Tuple;
import com.ssafy.herehear.entity.RegisteredMusic;

import java.time.LocalDateTime;
import java.util.List;

public interface TotalStatsRepository {

    List<Tuple> findByLikesSort(LocalDateTime startOfWeek, LocalDateTime endOfWeek);

    List<Tuple> findByTagsSort(LocalDateTime LocalDateTime, LocalDateTime endOfWeek);

    RegisteredMusic findByTopHistoryMusic(LocalDateTime startOfWeek, LocalDateTime endOfWeek);
}
