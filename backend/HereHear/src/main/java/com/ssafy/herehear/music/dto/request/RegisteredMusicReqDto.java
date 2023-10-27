package com.ssafy.herehear.music.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisteredMusicReqDto {
    private Double lng;
    private Double lat;
    private String comment;
    private String subject;
    private String singer;
    private String albumImg;
}
