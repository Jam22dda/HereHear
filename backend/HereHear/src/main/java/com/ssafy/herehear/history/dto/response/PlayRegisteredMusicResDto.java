package com.ssafy.herehear.history.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PlayRegisteredMusicResDto {
    private Long registeredMusicId;
    private String subject;
    private String singer;
    private String albumImg;
    private boolean like;
}
