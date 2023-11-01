package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.response.MusicInfoResDto;

import java.util.List;

public interface MusicService {

    List<MusicInfoResDto> getMusicInfoList(String keyword, Integer limit);

}
