package com.ssafy.herehear.music.dto.response.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    private String id;

    @JsonProperty("album_type")
    private String albumType;
    private List<Images> images;
    private String name;

    @JsonProperty("release_date")
    private String releaseDate;
    private String totalTracks;

}
