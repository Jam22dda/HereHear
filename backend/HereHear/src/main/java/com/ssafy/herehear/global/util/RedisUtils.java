package com.ssafy.herehear.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;
//    private final RedisTemplate<String, String> redisBlackListTemplate;

    public void set(String key, String o, int minutes) {
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(o.getClass()));
        redisTemplate.opsForValue().set(key, o, minutes, TimeUnit.MINUTES);
    }

    public void setHashValue(String hashKey, String field, String value) {
        redisTemplate.opsForHash().put(hashKey, field, value);
    }

    public String getHashValue(String hashKey, String field) {
        return (String) redisTemplate.opsForHash().get(hashKey, field);
    }

    public boolean deleteHashValue(String hashKey, String field) {
        return redisTemplate.opsForHash().delete(hashKey, field) == 1;
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

//    public void setBlackList(String key, String o, Long milliSeconds) {
////        redisBlackListTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(o.getClass()));
//        redisBlackListTemplate.opsForValue().set(key, o, milliSeconds, TimeUnit.MILLISECONDS);
//    }
//
//    public Object getBlackList(String key) {
//        return redisBlackListTemplate.opsForValue().get(key);
//    }
//
//    public boolean deleteBlackList(String key) {
//        return Boolean.TRUE.equals(redisBlackListTemplate.delete(key));
//    }
//
//    public boolean hasKeyBlackList(String key) {
//        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(key));
//    }
//
//    public void deleteAll() {
//        redisTemplate.delete(Objects.requireNonNull(redisTemplate.keys("*")));
//    }
}
