package com.ssafy.herehear.global.oauth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialProvider {
    GOOGLE("google"),
    NAVER("naver"),
    KAKAO("kakao"),
    SPOTIFY("spotify");

    private final String socialProvider;
}
