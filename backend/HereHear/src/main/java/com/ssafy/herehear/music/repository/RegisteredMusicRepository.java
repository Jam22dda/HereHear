package com.ssafy.herehear.music.repository;

import com.ssafy.herehear.entity.RegisteredMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredMusicRepository  extends JpaRepository<RegisteredMusic, Long> {

}

