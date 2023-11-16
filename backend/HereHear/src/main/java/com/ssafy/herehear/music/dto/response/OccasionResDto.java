package com.ssafy.herehear.music.dto.response;

import lombok.*;

@Getter
@Builder
@ToString
public class OccasionResDto {
    private Long occasionCode;
    private String occasionName;
    private String category;
}
