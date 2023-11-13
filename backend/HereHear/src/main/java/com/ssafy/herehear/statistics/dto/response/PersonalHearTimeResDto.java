package com.ssafy.herehear.statistics.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonalHearTimeResDto {
    private String mostTime;
    private int[] time;
}
