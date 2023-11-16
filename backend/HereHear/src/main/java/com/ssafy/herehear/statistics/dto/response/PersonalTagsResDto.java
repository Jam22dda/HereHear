package com.ssafy.herehear.statistics.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PersonalTagsResDto {
    private String nickname;
    private List<TagResDto> tagResDtoList;
}
