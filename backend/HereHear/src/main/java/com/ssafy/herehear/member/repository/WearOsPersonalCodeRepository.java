package com.ssafy.herehear.member.repository;

import com.ssafy.herehear.entity.WearOsPersonalCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WearOsPersonalCodeRepository extends JpaRepository<WearOsPersonalCode, Long> {
    Optional<WearOsPersonalCode> findByPersonalCode(String personalCode);

    Optional<WearOsPersonalCode> findByMember_MemberId(Long memberId);
}
