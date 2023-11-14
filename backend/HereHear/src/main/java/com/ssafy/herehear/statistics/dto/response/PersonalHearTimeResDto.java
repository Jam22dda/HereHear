package com.ssafy.herehear.statistics.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class PersonalHearTimeResDto {
    private String mostTime;
    private Map<String, Integer> time;
}
