package com.ssafy.herehear.report.repository;

import com.ssafy.herehear.entity.RegisteredMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("reportRegisteredMusicRepository")
public interface RegisteredMusicRepository extends JpaRepository<RegisteredMusic, Long> {
}
