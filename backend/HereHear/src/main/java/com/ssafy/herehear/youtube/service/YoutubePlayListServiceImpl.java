package com.ssafy.herehear.youtube.service;

import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class YoutubePlayListServiceImpl implements YoutubePlayListService {
    private static final String VALUE = "snippet";
    private static final String SEARCH_URL = "https://youtube.googleapis.com/youtube/v3";
    private static final String ACCESS_TOKEN = "Bearer ya29.a0AfB_byBV7hl9HWZWr99hT99Gf6vT2c9CFCd2dQBkB2X3nLhl8R6oqIoBQMVp5xWZX2V9Dd3UtOQ_zTHJ5M0nE7hcCebpH29fN8BtW3uPjlkSaT0BSCuRcn-DUwKWf2giFpBE3qTvkAJrmCs3bZ7GkQtSWluizgrGacwaCgYKAU0SARASFQHGX2MiOZl6zeCh0DubqCRHRzICJQ0170";
    private static final WebClient webClient = WebClient.builder().baseUrl(SEARCH_URL).build();

    @Override
    public String selectPlayList() {
        log.info("유튜브 재생목록 리스트 확인");
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlists")
                        .queryParam("part", VALUE)
                        .queryParam("mine", "true")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, ACCESS_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        //'HereHear' 재생 목록 유무 확인
        JSONObject jsonObject = new JSONObject(response.block());
        JSONArray items = jsonObject.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            JSONObject snippet = item.getJSONObject(VALUE);
            String title = snippet.getString("title");
            if ("HereHear".equals(title))
                return item.getString("id");
        }
        return insertPlayList();
    }

    @Override
    public String insertPlayList() {
        log.info("유튜브 재생목록 추가");
        String jsonBody = "{" +
                "\"snippet\": {" +
                "\"title\": \"HereHear\"," +
                "\"description\": \"New playlist description\"" +
                "}," +
                "\"status\": {" +
                "\"privacyStatus\": \"private\"" +
                "}" +
                "}";

        Mono<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlists")
                        .queryParam("part", VALUE)
                        .queryParam("part", "status")
                        .build())
                .body(BodyInserters.fromValue(jsonBody))
                .header(HttpHeaders.AUTHORIZATION, ACCESS_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        //추가한 재생 목록 Id 추출
        try {
            String result = response.block();
            JSONObject jsonResponse = new JSONObject(result);
            log.info("insertPlayList 완료");
            return jsonResponse.getString("id");
        } catch (Exception e) {
            throw new CustomException(ExceptionStatus.NOT_FOUND_YOUTUBE_PLAYLIST_ID);
        }
    }

    @Override
    public String selectPlayListItem(String searchName) {
        log.info("유튜브 영상 검색: "+searchName);
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("part", VALUE)
                        .queryParam("maxResults", "1")
                        .queryParam("q", searchName)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, ACCESS_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        //제일 상단 영상 추출
        JSONObject jsonObject = new JSONObject(response.block());
        JSONArray items = jsonObject.getJSONArray("items");
        JSONObject item = items.getJSONObject(0);
        JSONObject id = item.getJSONObject("id");

        return id.getString("videoId");
    }

    @Override
    public void addPlayListItem(String playlistId, String videoId) {
        log.info("[유튜브 재생목록에 영상 추가]: "+playlistId+", "+videoId);
        String jsonBody = "{"
                + "\"snippet\": {"
                + "\"playlistId\": \"" + playlistId + "\","
                + "\"resourceId\": {"
                + "\"kind\": \"youtube#video\","
                + "\"videoId\": \"" + videoId + "\""
                + "}"
                + "}"
                + "}";

        Mono<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlistItems")
                        .queryParam("part", VALUE)
                        .build())
                .body(BodyInserters.fromValue(jsonBody))
                .header(HttpHeaders.AUTHORIZATION, ACCESS_TOKEN)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        String result = response.block();
        log.info("addPlayListItem result: "+result);
    }

}