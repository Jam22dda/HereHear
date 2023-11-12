package com.ssafy.herehear.totalstats.repository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface TotalStatsRepository {

    List<Tuple> findByLikesSort();

}
