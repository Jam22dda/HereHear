package com.ssafy.herehear.global.oauth.service;

import com.ssafy.herehear.global.oauth.converter.ProviderUserRequest;
import com.ssafy.herehear.global.oauth.model.PrincipalUser;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("hello oidc user service");
        ClientRegistration clientRegistration = ClientRegistration.withClientRegistration(
                userRequest.getClientRegistration())
                .userNameAttributeName("sub")
                .build();
        log.info("clientRegi: {}", clientRegistration);

        OidcUserRequest oidcUserRequest = new OidcUserRequest(clientRegistration,
                userRequest.getAccessToken(),
                userRequest.getIdToken(),
                userRequest.getAdditionalParameters());

        log.info("oidcuser: {}", oidcUserRequest);

        OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
        OidcUser oidcUser = oidcUserService.loadUser(oidcUserRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oidcUser);
        ProviderUser providerUser = providerUser(providerUserRequest);

        log.info("providerUser: {}", providerUser);

        return new PrincipalUser(providerUser);
    }
}
