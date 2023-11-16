package com.ssafy.herehear.global.util;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final MemberRepository initMemberRepository;

    private static MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        memberRepository = initMemberRepository;
    }

    public static Member findMember(Long memberId) {
        return memberRepository.findByMemberIdAndNotRemoved(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));
    }
}
