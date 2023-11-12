package com.ssafy.herehear.totalstats.repository;

import com.querydsl.core.Tuple;
import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;

public interface TotalStatsRepository {

    List<Tuple> findByLikesSort();

    List<Tuple> findByTagsSort();

    RegisteredMusic findByTopHistoryMusic();
}
