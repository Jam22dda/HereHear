package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.global.util.JsonUtil;
import com.ssafy.herehear.music.dto.response.MusicInfoResDto;
import com.ssafy.herehear.music.dto.response.spotify.SpotifyMusic;
import com.ssafy.herehear.music.service.MusicService;
import lombok.RequiredArgsConstructor;
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

    private String accessToken;

    @Scheduled(fixedRate = 3000000) // 50분마다 실행 Spotify Access Token 재발급 (50분 = 3,000,000 밀리초)
    public void scheduleUpdateAccessToken() {
        updateAccessToken();
    }

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

}
