package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.global.util.JsonUtil;
import com.ssafy.herehear.global.util.RedisUtils;
import com.ssafy.herehear.music.dto.response.MusicInfoResDto;
import com.ssafy.herehear.music.dto.response.spotify.GetDevice;
import com.ssafy.herehear.music.dto.response.spotify.SpotifyMusic;
import com.ssafy.herehear.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class SpotifyMusicService implements MusicService {

    @Value("${musicApi.spotify.url.authUrl}")
    private String authUrl;

    @Value("${musicApi.spotify.url.searchUrl}")
    private String searchUrl;

    @Value("${musicApi.spotify.key.clientId}")
    private String clientId;

    @Value("${musicApi.spotify.key.clientSecret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.spotify.client-id}")
    private String memberClientId;

    @Value("${spring.security.oauth2.client.registration.spotify.client-secret}")
    private String memberClientSecret;

    private final RedisUtils redisUtils;

    private String accessToken;

    @Scheduled(fixedRate = 3000000) // 50분마다 실행 Spotify Access Token 재발급 (50분 = 3,000,000 밀리초)
    public void scheduleUpdateAccessToken() { updateAccessToken(); }

    @Override
    public List<MusicInfoResDto> getMusicInfoList(String keyword, Integer limit, Integer offset) {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();

        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search")
                        .queryParam("type", "track")
                        .queryParam("limit", limit)
                        .queryParam("q", keyword)
                        .queryParam("offset", offset)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        SpotifyMusic spotifyMusic = JsonUtil.fromJson(response.block(), SpotifyMusic.class);
        assert spotifyMusic != null;
        return MusicInfoResDto.toMusicInfoResDtoList(spotifyMusic);
    }

    private void updateAccessToken() {
        WebClient webClient = WebClient.builder().baseUrl(authUrl).build();
        Mono<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/token")
                        .build())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", clientId)
                        .with("client_secret", clientSecret))
                .retrieve()
                .bodyToMono(String.class);

        this.accessToken = JsonUtil.getValueAsString(response.block(), "access_token");
    }

    @Override
    public void updateMemberAccessToken(long memberId) {
        WebClient webClient = WebClient.builder().baseUrl(authUrl).build();
        String refreshToken = redisUtils.getHashValue("spotifyRefreshToken", String.valueOf(memberId));
        log.info(refreshToken);
        log.info(memberClientId);
        Mono<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("api/token")
                        .build())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "refresh_token")
                        .with("client_id", memberClientId)
                        .with("client_secret", memberClientSecret)
                        .with("refresh_token", refreshToken))
                .retrieve()
                .bodyToMono(String.class);

        String token = JsonUtil.getValueAsString(response.block(), "access_token");
        redisUtils.setHashValue("spotifyAccessToken", String.valueOf(memberId), token);
    }

    @Override
    public void changeVolume(long memberId, int volume) {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();
        webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("v1/me/player/volume")
                        .queryParam("volume_percent", volume)
                        //.queryParam("device_id", getDeviceId(memberId))
                        .build())
                .header("Authorization", "Bearer " + getToken(memberId))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public String getDeviceId(Long memberId) {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("v1/me/player/devices")
                        .build())
                .header("Authorization", "Bearer " + getToken(memberId))
                .retrieve()
                .bodyToMono(String.class);
        GetDevice getDevice = JsonUtil.fromJson(response.block(), GetDevice.class);

        return getDevice.getDevices().get(0).getId();
    }

    @Override
    public void startMusic(long memberId, String trackId) {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();

        String jsonBody = "{\"uris\": [\"" + trackId + "\"]}";

        webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("v1/me/player/play")
                        //.queryParam("device_id", getDeviceId(memberId))
                        .build())
                .header("Authorization", "Bearer " + getToken(memberId))
                .header("Content-Type", "application/json")
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    @Override
    public void pauseMusic(long memberId) {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();

        webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("v1/me/player/pause")
                        //.queryParam("device_id", getDeviceId(memberId))
                        .build())
                .header("Authorization", "Bearer " + getToken(memberId))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }

    @Override
    public void addMusic(long memberId, String trackId) {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();

        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("v1/me/player/queue")
                        .queryParam("uri", trackId)
                        .build())
                .header("Authorization", "Bearer " + getToken(memberId))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    @Override
    public String getToken(long memberId) {
        return redisUtils.getHashValue("spotifyAccessToken", String.valueOf(memberId));
    }
}
