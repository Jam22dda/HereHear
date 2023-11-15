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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class WearAuthServiceImpl implements WearAuthService {

    private final WearOsPersonalCodeRepository wearOsPersonalCodeRepository;

    @Override
    @Transactional
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
    @Transactional
    public WearPersonalCodeDto registerPersonalCode(Long memberId) {
        Member member = MemberUtil.findMember(memberId);
        Random random = new Random();

        String randomString = IntStream.generate(() -> 'A' + random.nextInt(26))
                .limit(6)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        WearOsPersonalCode wearOsPersonalCode = wearOsPersonalCodeRepository.findByMember_MemberId(member.getMemberId())
                .orElse(WearOsPersonalCode.builder()
                        .member(member)
                        .build());

        wearOsPersonalCode.updatePersonalCode(randomString);
        wearOsPersonalCode.updateLastModifiedDate(LocalDateTime.now());
        wearOsPersonalCodeRepository.save(wearOsPersonalCode);

        return WearPersonalCodeDto.builder()
                .personalCode(randomString).build();
    }

    @Override
    @Transactional
    public WearPersonalCodeDto getPersonalCode(Long memberId) {
        WearOsPersonalCode wearOsPersonalCode = wearOsPersonalCodeRepository.findByMember_MemberId(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.PERSONAL_CODE_IS_INVALID));

        return WearPersonalCodeDto.builder()
                .personalCode(wearOsPersonalCode.getPersonalCode())
                .build();
    }
}
