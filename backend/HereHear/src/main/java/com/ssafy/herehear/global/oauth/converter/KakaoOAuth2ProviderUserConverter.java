package com.ssafy.herehear.global.oauth.converter;

import com.ssafy.herehear.global.oauth.enums.AttributesKey;
import com.ssafy.herehear.global.oauth.enums.SocialProvider;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import com.ssafy.herehear.global.oauth.model.social.KakaoUser;
import com.ssafy.herehear.global.util.OAuth2Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class KakaoOAuth2ProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.getClientRegistration()
                .getRegistrationId()
                .equals(SocialProvider.KAKAO.getSocialProvider())) {
            return null;
        }

        if (providerUserRequest.getOAuth2User() instanceof OidcUser) {
            return null;
        }

        return new KakaoUser(
                OAuth2Utils.getOtherAttributes(
                        providerUserRequest.getOAuth2User(),
                        AttributesKey.KAKAO_SUB_ATTRIBUTES_KEY.getKey(),
                        AttributesKey.KAKAO_OTHER_ATTRIBUTES_KEY.getKey()),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration());
    }
}
