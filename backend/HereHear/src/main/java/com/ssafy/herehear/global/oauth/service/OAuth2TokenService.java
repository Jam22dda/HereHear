package com.ssafy.herehear.global.oauth.service;

import com.ssafy.herehear.global.util.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2TokenService {
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final RedisUtils redisUtils;

    public void setTokens(Authentication authentication, String provider, Long memberId) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        oauthToken.getAuthorizedClientRegistrationId(),
                        oauthToken.getName());

        if (client != null) {
            OAuth2AccessToken accessToken = client.getAccessToken();
            OAuth2RefreshToken refreshToken = client.getRefreshToken();
            redisUtils.setHashValue(provider+"AccessToken", memberId.toString(), accessToken.getTokenValue());
            log.info(provider + " Access Token: " + accessToken.getTokenValue());

            if(refreshToken != null) {
                redisUtils.setHashValue(provider+"RefreshToken", memberId.toString(), refreshToken.getTokenValue());
                log.info(provider + " Refresh Token: " + refreshToken.getTokenValue());
            }
        }
    }


}
