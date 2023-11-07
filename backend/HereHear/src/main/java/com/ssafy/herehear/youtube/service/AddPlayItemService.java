package com.ssafy.herehear.youtube.service;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddPlayItemService {

    public void addPlayItem(String videoId) throws GeneralSecurityException, IOException {
        YouTube youtubeService = YoutubeAuthorize.getService();

        // Define the PlaylistItem object, which will be uploaded as the request body.
        PlaylistItem playlistItem = new PlaylistItem();

        // Define and execute the API request
        YouTube.PlaylistItems.Insert request = youtubeService.playlistItems()
                .insert("snippet", playlistItem);
        PlaylistItem response = request.execute();
        log.info("addPlayItem: "+response);
    }

    public void selectPlayList() throws GeneralSecurityException, IOException {
        YouTube youtubeService = YoutubeAuthorize.getService();
        // Define and execute the API request
        YouTube.Playlists.List request = youtubeService.playlists()
                .list("");
        PlaylistListResponse response = request.execute();
        log.info("selectList: "+response);
    }

    public void insertPlayList() throws GeneralSecurityException, IOException {
        YouTube youtubeService = YoutubeAuthorize.getService();

        // Define the Playlist object, which will be uploaded as the request body.
        Playlist playlist = new Playlist();

        // Add the snippet object property to the Playlist object.
        PlaylistSnippet snippet = new PlaylistSnippet();
        snippet.setDescription("New playlist description");
        snippet.setTitle("HereHear");
        playlist.setSnippet(snippet);

        // Add the status object property to the Playlist object.
        PlaylistStatus status = new PlaylistStatus();
        status.setPrivacyStatus("private");
        playlist.setStatus(status);

        // Define and execute the API request
        YouTube.Playlists.Insert request = youtubeService.playlists()
                .insert("snippet,status", playlist);
        Playlist response = request.execute();
        log.info("insertList: "+response);
    }
}