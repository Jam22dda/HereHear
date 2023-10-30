package com.ssafy.herehear.music.dto.response;

import com.ssafy.herehear.entity.Occasion;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class RegisteredMusicDetailsResDto {
    private Long registeredMusicId;
    private Double lng;
    private Double lat;
    private String comment;
    private String subject;
    private String singer;
    private String albumImg;
    private String releaseTime;
    private String createTime;
    private String nickname;
    private boolean like;
    private List<Occasion> occasions;
}