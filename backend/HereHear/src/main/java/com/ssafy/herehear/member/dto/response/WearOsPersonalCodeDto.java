package com.ssafy.herehear.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WearOsPersonalCodeDto {

    private Boolean accessResult;
    private String personalCode;
    private Long userId;

    @Builder
    public WearOsPersonalCodeDto(Boolean accessResult, String personalCode, Long userId) {
        this.accessResult = accessResult;
        this.personalCode = personalCode;
        this.userId = userId;
    }
}
