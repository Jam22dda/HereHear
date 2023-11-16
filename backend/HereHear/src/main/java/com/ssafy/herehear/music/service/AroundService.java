package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.response.*;

import java.util.List;

public interface AroundService {

    List<AroundMusicResDto> getAroundMusicList(Double lat, Double lng);

    List<AroundMusicResDto> getAroundSearchMusic(AroundSearchReqDto aroundSearchReqDto);
}
