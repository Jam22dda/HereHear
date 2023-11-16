package com.ssafy.herehear.totalstats.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TotalStatsMusicResDto {
    private Long registeredMusicId;
    private String subject;
    private String singer;
    private String albumImg;
}

