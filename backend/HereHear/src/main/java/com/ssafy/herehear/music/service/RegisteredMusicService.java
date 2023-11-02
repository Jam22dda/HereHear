package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;

import java.util.List;

public interface RegisteredMusicService {

    SseResDto registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto);

    List<OccasionResDto> getAllTags();

    RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId);

    List<RegisteredMusicResDto> getRegisteredMusicList();

    SseResDto updateMyRegisteredMusic(long memberId, long registeredMusicId);

    List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId);
}
