package com.ssafy.herehear.global.oauth.service;

import com.ssafy.herehear.global.oauth.converter.ProviderUserConverter;
import com.ssafy.herehear.global.oauth.converter.ProviderUserRequest;
import com.ssafy.herehear.global.oauth.model.ProviderUser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    protected ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
        return providerUserConverter.convert(providerUserRequest);
    }
}
