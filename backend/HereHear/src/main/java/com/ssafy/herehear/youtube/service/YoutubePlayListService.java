package com.ssafy.herehear.youtube.service;

public interface YoutubePlayListService {

    String selectPlayList(String accessToken);

    String insertPlayList(String accessToken);

    String selectPlayItem(String searchName, String accessToken);

    Boolean selectPlayListItem(String playlistId, String videoId, String accessToken);

    void addPlayListItem(String playlistId, String videoId, String accessToken);
}
