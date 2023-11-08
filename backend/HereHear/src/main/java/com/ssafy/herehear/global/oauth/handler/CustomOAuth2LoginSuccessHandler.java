package com.ssafy.herehear.global.oauth.handler;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.global.oauth.model.PrincipalUser;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import com.ssafy.herehear.global.util.CookieUtil;
import com.ssafy.herehear.global.util.JwtProvider;
import com.ssafy.herehear.member.mapper.MemberMapper;
import com.ssafy.herehear.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtProvider jwtProvider;

    @Value("${auth.redirectUrl}")
    private final String REDIRECT_ENDPOINT;
    private String redirectUrl;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        PrincipalUser principalUser = (PrincipalUser)authentication.getPrincipal();
        ProviderUser providerUser = principalUser.getProviderUser();
        log.info("provider: {}", providerUser.getProvider());

        Optional<Member> member = memberRepository.findByEmailAndProvider(providerUser.getEmail(),
                providerUser.getProvider());

        member.ifPresentOrElse(
                tempMember -> {
                    if (tempMember.getRemoveDate() != null) {
                        log.info("=== 탈퇴한 회원입니다. 가입 페이지로 리다이렉트 합니다. ===");
                        redirectUrl = REDIRECT_ENDPOINT + "/memberInfo?id=" + tempMember.getMemberId();

                    }else {
                        if (tempMember.getProfileCharacter() == null) {
                            log.info("============ 캐릭터 선택 안 함 {} ============", tempMember.getMemberId());
                            redirectUrl = REDIRECT_ENDPOINT + "/memberInfo?id=" + tempMember.getMemberId();
                        } else {
                            String accessToken = jwtProvider.createAccessToken(member.get());
                            String refreshToken = jwtProvider.createRefreshToken();

                            Cookie cookie = CookieUtil.createCookie(refreshToken);
                            response.addCookie(cookie);

                            log.info("============ 기존 회원 {} ============", tempMember.getMemberId());
                            redirectUrl = REDIRECT_ENDPOINT + "/oauth2/redirect?token=" + accessToken;
                        }
                    }
                },
                () -> {
                    Member newMember = memberMapper.toMember(providerUser.getUserName(), providerUser.getEmail(), providerUser.getProvider());
                    log.info("{}{}{}", newMember.getEmail(), newMember.getNickname(), providerUser.getProvider());
                    Member signupMember = memberRepository.save(newMember);
                    log.info("============ 최초 진입 {} ============", signupMember.getMemberId());
                    redirectUrl = REDIRECT_ENDPOINT + "/memberInfo?id=" + signupMember.getMemberId();
                }
        );

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

}
