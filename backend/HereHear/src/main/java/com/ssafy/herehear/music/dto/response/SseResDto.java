package com.ssafy.herehear.music.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class SseResDto {
    private int status;//0:삭제//1:등록
    private Long registeredMusicId;
    private Double lng;
    private Double lat;
    private String subject;
    private String singer;
    private String albumImg;
    private LocalDateTime createTime;
}
