package com.ssafy.herehear.music.dto.response.spotify;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Devices {
    private String id;

    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("is_private_session")
    private Boolean isPrivateSession;
    private String name;
    @JsonProperty("supports_volume")
    private Boolean supportsVolume;
    private String type;
    @JsonProperty("volume_percent")
    private Integer volumePercent;


    @JsonProperty("track_number")
    private int trackNumber;

}
