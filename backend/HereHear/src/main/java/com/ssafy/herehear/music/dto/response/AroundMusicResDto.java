package com.ssafy.herehear.music.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class AroundMusicResDto {
    private Long registeredMusicId;
    private String subject;
    private String singer;
    private String albumImg;
    private List<String> occasionName;
}
