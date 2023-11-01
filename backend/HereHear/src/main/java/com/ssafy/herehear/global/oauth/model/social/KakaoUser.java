package com.ssafy.herehear.global.oauth.model.social;

import com.ssafy.herehear.global.oauth.model.Attributes;
import com.ssafy.herehear.global.oauth.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {

    Map<String, Object> profileAttribute;

    public KakaoUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getSubAttributes(), oAuth2User, clientRegistration);
        this.profileAttribute = attributes.getOtherAttributes();
    }

    @Override
    public String getId() {
        return (String)getAttributes().get("id");
    }

    @Override
    public String getUserName() {
        return (String)profileAttribute.get("nickname");
    }
}
