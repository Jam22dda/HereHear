package com.ssafy.herehear.totalstats.repository;

import com.ssafy.herehear.entity.RegisteredMusic;

import java.util.List;

public interface MonthlyStatisticsRepository {

    List<RegisteredMusic> findByMonthlyRegisteredMusics();

}
