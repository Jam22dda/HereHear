package com.ssafy.herehear.youtube.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddPlayItemService {

    private String searchUrl = "https://youtube.googleapis.com/youtube/v3";

    private String accessToken = "ya29.a0AfB_byC8E-k3ZOkI4Lv5d3pRRMo9TMCjvf4Cim0x7kJfKFR8hiXm3kD7FauojYjAlQ86-pJ5dn9Y29AxUShU5W2pS0Q0pkUxaFBzRXrx6Ap1HQw__ibVeYOCoeJ7PeCzwOya30jdsUiWSrxkKKPIsLYllDEVpw-jyAaCgYKAQ8SARASFQHGX2Miy1FVFY_Wn2M76Y4dM8cGrw0169";

    public String selectPlayList() {
        WebClient webClient = WebClient.builder().baseUrl(searchUrl).build();

        Mono<String> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlists")
                        .queryParam("part", "snippet")
                        .queryParam("mine", "true")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        String listId = "";
        JSONObject jsonObject = new JSONObject(response.block());

        JSONArray items = jsonObject.getJSONArray("items");
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            JSONObject snippet = item.getJSONObject("snippet");
            String title = snippet.getString("title");
            if ("HereHear".equals(title))
                listId = item.getString("id");
        }

        log.info("listId: "+listId);
        return listId;
    }

//    public void addPlayItem(String videoId) throws GeneralSecurityException, IOException {
//        YouTube youtubeService = YoutubeAuthorize.getService();
//
//        // Define the PlaylistItem object, which will be uploaded as the request body.
//        PlaylistItem playlistItem = new PlaylistItem();
//
//        // Define and execute the API request
//        YouTube.PlaylistItems.Insert request = youtubeService.playlistItems()
//                .insert("snippet", playlistItem);
//        PlaylistItem response = request.execute();
//        log.info("addPlayItem: " + response);
//    }
//
//    public void insertPlayList() throws GeneralSecurityException, IOException {
//        YouTube youtubeService = YoutubeAuthorize.getService();
//
//        // Define the Playlist object, which will be uploaded as the request body.
//        Playlist playlist = new Playlist();
//
//        // Add the snippet object property to the Playlist object.
//        PlaylistSnippet snippet = new PlaylistSnippet();
//        snippet.setDescription("New playlist description");
//        snippet.setTitle("HereHear");
//        playlist.setSnippet(snippet);
//
//        // Add the status object property to the Playlist object.
//        PlaylistStatus status = new PlaylistStatus();
//        status.setPrivacyStatus("private");
//        playlist.setStatus(status);
//
//        // Define and execute the API request
//        YouTube.Playlists.Insert request = youtubeService.playlists()
//                .insert("snippet,status", playlist);
//        Playlist response = request.execute();
//        log.info("insertList: " + response);
//    }
}