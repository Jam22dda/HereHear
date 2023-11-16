package com.ssafy.herehear.totalstats.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TotalStatsTagsResDto {
    private String tagName;
    private Long tagCount;
}

