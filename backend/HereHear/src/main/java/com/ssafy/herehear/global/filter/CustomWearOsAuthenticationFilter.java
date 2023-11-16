package com.ssafy.herehear.global.filter;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.WearOsPersonalCode;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.member.repository.WearOsPersonalCodeRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class CustomWearOsAuthenticationFilter extends OncePerRequestFilter {

    private WearOsPersonalCodeRepository wearOsPersonalCodeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String personalCode = request.getHeader("personalCode");
        log.info("[WearOS Filter] personalCode: {}", personalCode);

        if (personalCode != null && !personalCode.equals("")) {
            WearOsPersonalCode wearOsPersonalCode = wearOsPersonalCodeRepository.findByPersonalCode(personalCode)
                    .orElseThrow(() -> new CustomException(ExceptionStatus.PERSONAL_CODE_IS_INVALID));

            String findId = String.valueOf(wearOsPersonalCode.getMember().getMemberId());

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(findId, null, null);
            log.info("[WearOS Filter] memberId={}", findId);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
