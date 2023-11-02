package com.ssafy.herehear.music.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
public class RegisteredMusicResDto {
    private Long registeredMusicId;
    private Double lng;
    private Double lat;
    private String subject;
    private String singer;
    private String albumImg;
    private List<String> occasionName;
}
