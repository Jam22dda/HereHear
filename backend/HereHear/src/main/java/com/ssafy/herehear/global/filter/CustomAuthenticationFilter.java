package com.ssafy.herehear.global.filter;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.JwtProvider;
import com.ssafy.herehear.member.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private JwtProvider jwtProvider;
    private MemberRepository memberRepository;

    public static final String TOKEN_EXCEPTION_KEY = "exception";
    public static final String TOKEN_INVALID = "invalid";
    public static final String TOKEN_EXPIRE = "expire";
    public static final String TOKEN_UNSUPPORTED = "unsupported";
    public static final String TOKEN_ILLEGAL = "illegal";
    public static final String CUSTOM_EXCEPTION = "custom";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String accessToken = jwtProvider.getAccessToken(request);
            if (StringUtils.hasText(accessToken) && jwtProvider.validatateToken(accessToken)) {
                if (jwtProvider.isExpired(accessToken)) {
                    throw new CustomException(ExceptionStatus.TOKEN_EXPIRED);
                }

                Long memberId = jwtProvider.getClaimFromToken(accessToken, "memberId");

				/*
				우선 Member 객체 그대로 넣음
				 */
                Member findMember = memberRepository.findById(memberId).orElseThrow(
                        () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
                );

                String findId = String.valueOf(findMember.getMemberId());
                log.info("memberId={}", findMember.getMemberId());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(findId, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (MalformedJwtException e) {
            log.info("유효하지 않는 토큰입니다.");
            request.setAttribute(TOKEN_EXCEPTION_KEY, TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다.");
            request.setAttribute(TOKEN_EXCEPTION_KEY, TOKEN_EXPIRE);
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
            request.setAttribute(TOKEN_EXCEPTION_KEY, TOKEN_UNSUPPORTED);
        } catch (IllegalStateException e) {
            log.info("잘못된 토큰입니다.");
            request.setAttribute(TOKEN_EXCEPTION_KEY, TOKEN_ILLEGAL);
        } catch (CustomException e) {
            log.info("커스텀 예외");
            request.setAttribute(TOKEN_EXCEPTION_KEY, CUSTOM_EXCEPTION);
        } catch (Exception e) {
            request.setAttribute(TOKEN_EXCEPTION_KEY, TOKEN_INVALID);
        }

        filterChain.doFilter(request, response);
    }
}
