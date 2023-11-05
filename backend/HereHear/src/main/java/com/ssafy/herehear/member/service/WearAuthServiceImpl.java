package com.ssafy.herehear.member.service;

import com.ssafy.herehear.entity.WearOsPersonalCode;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.member.dto.response.WearOsPersonalCodeDto;
import com.ssafy.herehear.member.repository.WearOsPersonalCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WearAuthServiceImpl implements WearAuthService {

    private final WearOsPersonalCodeRepository wearOsPersonalCodeRepository;

    @Override
    public WearOsPersonalCodeDto personalCodeValidation(String personalCode) {
        WearOsPersonalCode wearOsPersonalCode = wearOsPersonalCodeRepository.findByPersonalCode(personalCode)
                .orElseThrow(() -> new CustomException(ExceptionStatus.PERSONAL_CODE_IS_INVALID));

        return WearOsPersonalCodeDto.builder()
                .accessResult(true)
                .userId(wearOsPersonalCode.getMember().getMemberId())
                .personalCode(wearOsPersonalCode.getPersonalCode())
                .build();
    }
}
