package com.ssafy.herehear.global.oauth.converter;

import com.ssafy.herehear.global.oauth.enums.SocialProvider;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import com.ssafy.herehear.global.oauth.model.social.SpotifyUser;
import com.ssafy.herehear.global.util.OAuth2Utils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpotifyOAuth2ProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    @Override
    public ProviderUser convert(ProviderUserRequest providerUserRequest) {

        if(!providerUserRequest.getClientRegistration()
                .getRegistrationId()
                .equals(SocialProvider.SPOTIFY.getSocialProvider())) {
            return null;
        }

        return new SpotifyUser(
                OAuth2Utils.getMainAttributes(providerUserRequest.getOAuth2User()),
                providerUserRequest.getOAuth2User(),
                providerUserRequest.getClientRegistration()
        );
    }
}
