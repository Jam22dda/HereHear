package com.ssafy.herehear.member.service;

import com.ssafy.herehear.member.dto.response.WearOsPersonalCodeDto;

public interface WearAuthService {
    WearOsPersonalCodeDto personalCodeValidation(String personalCode);
}
