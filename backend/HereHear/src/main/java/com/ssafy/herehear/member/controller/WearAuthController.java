package com.ssafy.herehear.member.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.member.dto.response.WearOsPersonalCodeDto;
import com.ssafy.herehear.member.service.WearAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WearAuthController {

    private final WearAuthService wearAuthService;

    @PostMapping("/wearos/auth/{personalCode}")
    public DataResponse<WearOsPersonalCodeDto> wearAuth(@PathVariable String personalCode) {
        log.info("[WearOS Authentication request] personalCode: {}", personalCode);

        WearOsPersonalCodeDto wearOsPersonalCodeDto = wearAuthService.personalCodeValidation(personalCode);

        return new DataResponse<>("200", "인증 성공", wearOsPersonalCodeDto);
    }

}
