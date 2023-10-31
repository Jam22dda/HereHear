package com.ssafy.herehear.music.dto.response.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    private String id;
    private String albumType;
    private List<Images> images;
    private String name;
    private String releaseDate;
    private String totalTracks;

}
