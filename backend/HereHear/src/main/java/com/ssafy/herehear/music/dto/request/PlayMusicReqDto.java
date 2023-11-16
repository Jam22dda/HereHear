package com.ssafy.herehear.music.dto.request;

import lombok.Getter;

@Getter
public class PlayMusicReqDto {
    private String trackId;
    private int positionMs;
}
