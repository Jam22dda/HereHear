package com.ssafy.herehear.global.oauth.model.social;

import com.ssafy.herehear.global.oauth.model.Attributes;
import com.ssafy.herehear.global.oauth.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class NaverUser extends OAuth2ProviderUser {

    public NaverUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getSubAttributes(),
                oAuth2User,
                clientRegistration);
    }

    @Override
    public String getId() {
        return (String) getAttributes().get("email");
    }

    @Override
    public String getUserName()  {
        return (String) getAttributes().get("name");
    }

}