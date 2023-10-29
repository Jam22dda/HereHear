package com.ssafy.herehear.music.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LikeRegisteredMusicResDto {
    private Long registeredMusicId;
    private String subject;
    private String singer;
    private String albumImg;
    private boolean like;
}
