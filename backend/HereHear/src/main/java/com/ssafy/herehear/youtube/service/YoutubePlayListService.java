package com.ssafy.herehear.youtube.service;

public interface YoutubePlayListService {

    String selectPlayList();

    String insertPlayList();

    String selectPlayListItem(String searchName);

    void addPlayListItem(String playlistId, String videoId);
}
