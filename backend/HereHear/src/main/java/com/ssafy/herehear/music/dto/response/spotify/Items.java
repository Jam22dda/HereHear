package com.ssafy.herehear.music.dto.response.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    private String id;
    private String name;

    @JsonProperty("track_number")
    private int trackNumber;

    @JsonProperty("duration_ms")
    private int durationMs;

    private Album album;
    private List<Artists> artists;

}
