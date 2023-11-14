package com.ssafy.herehear.member.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.member.dto.response.WearOsPersonalCodeDto;
import com.ssafy.herehear.member.dto.response.WearPersonalCodeDto;
import com.ssafy.herehear.member.service.WearAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/wearos/personalCode")
    public DataResponse<WearPersonalCodeDto> getPersonalCode(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getPrincipal().toString());
        log.info("[WearOS PersonalCode 발급] memberId: {}", memberId);

        WearPersonalCodeDto personalCode = wearAuthService.getPersonalCode(memberId);
        return new DataResponse<>("200", "PersonalCode 발급 성공", personalCode);
    }

}
