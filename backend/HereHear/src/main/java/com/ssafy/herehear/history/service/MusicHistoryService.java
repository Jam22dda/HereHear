package com.ssafy.herehear.history.service;

import com.ssafy.herehear.history.dto.response.LikeRegisteredMusicResDto;

import java.util.List;

public interface MusicHistoryService {

    void registerPlayMusic(long memberId, long registeredMusicId);

    void deletePlayMusic(long memberId, long registeredMusicId);

    List<LikeRegisteredMusicResDto> getMusicHistoryList(long memberId);
}