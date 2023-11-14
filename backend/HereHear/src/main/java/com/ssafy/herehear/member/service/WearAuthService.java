package com.ssafy.herehear.member.service;

import com.ssafy.herehear.member.dto.response.WearOsPersonalCodeDto;
import com.ssafy.herehear.member.dto.response.WearPersonalCodeDto;

public interface WearAuthService {
    WearOsPersonalCodeDto personalCodeValidation(String personalCode);

    WearPersonalCodeDto getPersonalCode(Long memberId);
}
