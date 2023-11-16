package com.ssafy.herehear.music.service;

import com.ssafy.herehear.music.dto.request.PlayMusicReqDto;
import com.ssafy.herehear.music.dto.response.MusicInfoResDto;

import java.util.List;

public interface MusicService {

    List<MusicInfoResDto> getMusicInfoList(String keyword, Integer limit, Integer offset);
    String getDeviceId(Long memberId);
    void startMusic(long memberId, PlayMusicReqDto musicReqDto);
    int pauseMusic(long memberId);
    void addMusic(long memberId, String trackId);
    String getToken(long memberId);
    void updateMemberAccessToken(long memberId);
    void changeVolume(long memberId, int volume);

}
