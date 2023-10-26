package com.ssafy.herehear.music.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RegisteredMusicResDto {
    private Long registeredMusicId;
    private Double lng;
    private Double lat;
    private String comment;
    private String subject;
    private String singer;
    private String albumImg;
}
