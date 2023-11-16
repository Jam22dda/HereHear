package com.ssafy.herehear.global.oauth.converter;

import com.ssafy.herehear.global.oauth.enums.AttributesKey;
import com.ssafy.herehear.global.oauth.enums.SocialProvider;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import com.ssafy.herehear.global.oauth.model.social.NaverUser;
import com.ssafy.herehear.global.util.OAuth2Utils;

public class NaverOAuth2ProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.getClientRegistration().getRegistrationId().equals(SocialProvider.NAVER.getSocialProvider())) {
            return null;
        }
        return new NaverUser(
                OAuth2Utils.getSubAttributes(providerUserRequest.getOAuth2User(), AttributesKey.NAVER_SUB_ATTRIBUTES_KEY.getKey()),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration());
    }
}
