package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.request.AroundMusicReqDto;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;

import java.util.List;

public interface RegisteredMusicService {

    void registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto);

    List<OccasionResDto> getTag();

    RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId);

    List<RegisteredMusicResDto> getRegisteredMusicList();

    void updateMyRegisteredMusic(long memberId, long registeredMusicId);

    List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId);

    List<AroundMusicResDto> getAroundMusicList(AroundMusicReqDto aroundMusicReqDto);
}
