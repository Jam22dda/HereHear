package com.ssafy.herehear.global.oauth.converter;

public interface ProviderUserConverter<T, R> {

    R convert(T t);
}
