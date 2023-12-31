package com.ssafy.herehear.history.repository;

import com.ssafy.herehear.entity.MemberMusicId;
import com.ssafy.herehear.entity.MusicHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicHistoryRepository extends JpaRepository<MusicHistory, MemberMusicId> {
}
