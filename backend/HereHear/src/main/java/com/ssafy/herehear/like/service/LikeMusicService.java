package com.ssafy.herehear.like.service;

import com.ssafy.herehear.like.dto.response.LikeRegisteredMusicResDto;
import java.util.List;

public interface LikeMusicService {

    void registerlikeMusic(Long memberId, Long registeredMusicId);

    List<LikeRegisteredMusicResDto> likeMusicList(long memberId, String contents);

}