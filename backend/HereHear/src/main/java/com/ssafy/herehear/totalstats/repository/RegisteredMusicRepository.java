package com.ssafy.herehear.totalstats.repository;

import com.ssafy.herehear.entity.RegisteredMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("MonthlyRegisteredMusicRepository")
public interface RegisteredMusicRepository extends JpaRepository<RegisteredMusic, Long> {

}