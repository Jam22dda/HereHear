package com.ssafy.herehear.music.dto.response;

import com.ssafy.herehear.entity.Occasion;
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
    private List<Occasion> occasions;
}
