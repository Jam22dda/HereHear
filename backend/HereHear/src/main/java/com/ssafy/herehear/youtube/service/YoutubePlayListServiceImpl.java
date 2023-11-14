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
    private static final String ITEMS = "items";
    private static final String SEARCH_URL = "https://youtube.googleapis.com/youtube/v3";
    private static final WebClient webClient = WebClient.builder().baseUrl(SEARCH_URL).build();

    @Override
    public String selectPlayList(String accessToken) {
        log.info("[유튜브 재생목록 리스트 확인]");
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlists")
                        .queryParam("part", VALUE)
                        .queryParam("mine", "true")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        //'HereHear' 재생 목록 유무 확인
        JSONObject jsonObject = new JSONObject(response.block());
        JSONArray itemsArray = jsonObject.getJSONArray(ITEMS);
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);
            JSONObject snippet = item.getJSONObject(VALUE);
            String title = snippet.getString("title");
            if ("HereHear".equals(title))
                return item.getString("id");
        }
        return insertPlayList(accessToken);
    }

    @Override
    public String insertPlayList(String accessToken) {
        log.info("[유튜브 재생목록 추가]");
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
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        //추가한 재생 목록 Id 추출
        try {
            String result = response.block();
            JSONObject jsonResponse = new JSONObject(result);
            log.info("[유튜브 재생목록 추가] 완료");
            return jsonResponse.getString("id");
        } catch (Exception e) {
            throw new CustomException(ExceptionStatus.NOT_FOUND_YOUTUBE_PLAYLIST_ID);
        }
    }

    @Override
    public String selectPlayItem(String searchName,String accessToken) {
        log.info("[유튜브 영상 검색] searchName: {}",searchName);
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("part", VALUE)
                        .queryParam("maxResults", "1")
                        .queryParam("q", searchName)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        //제일 상단 영상 추출
        JSONObject jsonObject = new JSONObject(response.block());
        JSONArray itemsArray = jsonObject.getJSONArray(ITEMS);
        JSONObject item = itemsArray.getJSONObject(0);
        JSONObject id = item.getJSONObject("id");

        return id.getString("videoId");
    }

    @Override
    public Boolean selectPlayListItem(String playlistId, String targetVideoId, String accessToken) {
        log.info("[HereHear 재생목록 해당 영상 유무 확인] playlistId:{}, targetVideoId:{}, accessToken:{}",playlistId,targetVideoId,accessToken);
        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlistItems")
                        .queryParam("part", VALUE)
                        .queryParam("playlistId", playlistId)
                        .build())
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        JSONObject jsonObject = new JSONObject(response.block());
        JSONArray itemsArray = jsonObject.getJSONArray(ITEMS);
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.getJSONObject(i);
            JSONObject snippet = item.getJSONObject(VALUE);
            JSONObject resourceId = snippet.getJSONObject("resourceId");
            String videoId = resourceId.getString("videoId");

            if (targetVideoId.equals(videoId)) {
                log.info("[HereHear 재생목록 해당 영상 유무 확인] 완료 - 일치하는 영상 존재");
                return true;
            }
        }

        return false;
    }

    @Override
    public void addPlayListItem(String playlistId, String videoId, String accessToken) {
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
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        String result = response.block();
        log.info("addPlayListItem result: "+result);
    }

}