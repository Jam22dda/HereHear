package com.ssafy.herehear.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WearPersonalCodeDto {
    private String personalCode;
}
