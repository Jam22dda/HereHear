package com.ssafy.herehear.global.oauth.converter;

import com.ssafy.herehear.global.oauth.enums.SocialProvider;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import com.ssafy.herehear.global.oauth.model.social.KakaoOidcUser;
import com.ssafy.herehear.global.util.OAuth2Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class KakaoOAuth2OidcProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.getClientRegistration()
                .getRegistrationId()
                .equals(SocialProvider.KAKAO.getSocialProvider())) {
            return null;
        }

        if (!(providerUserRequest.getOAuth2User() instanceof OidcUser)) {
            return null;
        }

        return new KakaoOidcUser(
                OAuth2Utils.getMainAttributes(
                        providerUserRequest.getOAuth2User()),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration());
    }
}
