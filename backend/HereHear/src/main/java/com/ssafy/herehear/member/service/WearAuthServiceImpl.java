package com.ssafy.herehear.member.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.WearOsPersonalCode;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.member.dto.response.WearOsPersonalCodeDto;
import com.ssafy.herehear.member.dto.response.WearPersonalCodeDto;
import com.ssafy.herehear.member.repository.WearOsPersonalCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public WearPersonalCodeDto getPersonalCode(Long memberId) {
        Member member = MemberUtil.findMember(memberId);

        StringBuilder sb = new StringBuilder(6);
        while (true) {
            for (int i = 0; i < 6; i++) {
                long rand = (long) Math.floor(Math.round(Math.random() * 26)) + 65;
                sb.append((char) rand);
            }

            if (wearOsPersonalCodeRepository.findByPersonalCode(sb.toString()).isEmpty()) {
                break;
            }
        }

        WearOsPersonalCode wearOsPersonalCode = wearOsPersonalCodeRepository.findByMember_MemberId(member.getMemberId())
                .orElse(WearOsPersonalCode.builder()
                        .member(member)
                        .build());

        wearOsPersonalCode.updatePersonalCode(sb.toString());
        wearOsPersonalCode.updateLastModifiedDate(LocalDateTime.now());
        wearOsPersonalCodeRepository.save(wearOsPersonalCode);

        return WearPersonalCodeDto.builder()
                .personalCode(sb.toString()).build();
    }
}
