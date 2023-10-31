package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.MyRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.OccasionResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicDetailsResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;

import java.util.List;

public interface RegisteredMusicService {

    void registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto);

    List<OccasionResDto> getTag();

    RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId);

    List<RegisteredMusicResDto> getRegisteredMusicList();

    void updateMyRegisteredMusic(long memberId, long registeredMusicId);

    List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId);


}
