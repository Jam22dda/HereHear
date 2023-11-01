package com.ssafy.herehear.music.dto.request;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class AroundSearchReqDto {
    private Double lng;
    private Double lat;
    private List<Long> occasions;
    private String keyword;
}
