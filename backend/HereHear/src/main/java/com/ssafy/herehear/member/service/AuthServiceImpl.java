package com.ssafy.herehear.member.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.CookieUtil;
import com.ssafy.herehear.global.util.JwtProvider;
import com.ssafy.herehear.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtProvider jwtProvider;
    private final CookieUtil cookieUtil;
    private final String REFRESH_TOKEN = "refreshToken";
    private final MemberRepository memberRepository;

    @Override
    public String refresh(HttpServletRequest request) {
        String accessToken = jwtProvider.getAccessToken(request);
        Cookie cookie = cookieUtil.getCookie(request, REFRESH_TOKEN).orElseThrow(
                () -> new CustomException(ExceptionStatus.TOKEN_NOT_FOUND_IN_COOKIE)
        );

        // 만료가 안 되었을 때
        if(!jwtProvider.isExpired(cookie.getValue())) {
            Long memberId = jwtProvider.getClaimFromExpirationToken(accessToken, "memberId");
            Member findMember = memberRepository.findById(memberId).orElseThrow(
                    () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
            );
            String newAccessToken = jwtProvider.createAccessToken(findMember);
            return newAccessToken;
        }

        // 만료 O
        throw new CustomException(ExceptionStatus.REFRESH_TOKEN_EXPIRED);
    }
}
