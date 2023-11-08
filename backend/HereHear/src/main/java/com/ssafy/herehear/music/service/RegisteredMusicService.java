package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;

import java.util.List;

public interface RegisteredMusicService {

    List<SseResDto> registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto);

    List<OccasionResDto> getAllTags();

    RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId);

    List<RegisteredMusicMapResDto> getRegisteredMusicList();

    List<SseResDto> updateMyRegisteredMusic(long memberId, long registeredMusicId);

    List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId);
}
