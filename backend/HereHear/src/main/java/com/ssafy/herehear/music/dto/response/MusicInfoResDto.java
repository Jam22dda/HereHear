package com.ssafy.herehear.music.dto.response;

import com.ssafy.herehear.music.dto.response.spotify.Artists;
import com.ssafy.herehear.music.dto.response.spotify.Images;
import com.ssafy.herehear.music.dto.response.spotify.SpotifyMusic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class MusicInfoResDto {

    private String trackId;
    private String subject;
    private String duration;

    private String albumId;
    private String albumType;
    private List<Images> albumImages;
    private String albumName;
    private String releaseDate;

    private List<Artists> artists;

    public static List<MusicInfoResDto> toMusicInfoResDtoList(SpotifyMusic spotifyMusic) {
        List<MusicInfoResDto> result = new ArrayList<>();

        spotifyMusic.getTracks().getItems()
                .forEach(item -> result.add(MusicInfoResDto.builder()
                        .trackId(item.getId())
                        .subject(item.getName())
                        .duration(msToMMssFormat(String.valueOf(item.getDurationMs())))
                        .albumId(item.getAlbum().getId())
                        .albumType(item.getAlbum().getAlbumType())
                        .albumImages(item.getAlbum().getImages())
                        .albumName(item.getAlbum().getName())
                        .releaseDate(item.getAlbum().getReleaseDate())
                        .artists(item.getArtists())
                        .build()));

        return result;
    }

    private static String msToMMssFormat(String ms) {
        int seconds = Integer.parseInt(ms) / 1000;
        int minutes = seconds / 60;
        seconds = seconds % 60;

        return String.format("%d:%02d", minutes, seconds);
    }
}
