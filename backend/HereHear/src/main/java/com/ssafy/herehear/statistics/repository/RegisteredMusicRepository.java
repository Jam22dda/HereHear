package com.ssafy.herehear.statistics.repository;

import com.ssafy.herehear.entity.RegisteredMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("statisticsRegisteredMusicRepository")
public interface RegisteredMusicRepository extends JpaRepository<RegisteredMusic, Long> {
}
