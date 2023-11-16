package com.ssafy.herehear.youtube.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class YoutubePlayReqDto {

    @NotEmpty(message = "검색어는 필수 입력값 입니다")
    @Size(max = 200, message = "최대 200자까지 입력이 가능합니다")
    private String searchName;

}
