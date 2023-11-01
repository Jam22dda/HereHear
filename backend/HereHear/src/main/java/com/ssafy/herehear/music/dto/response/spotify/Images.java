package com.ssafy.herehear.music.dto.response.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    private int height;
    private String url;
    private int width;
}
