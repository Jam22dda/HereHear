package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeMusic {

    @EmbeddedId
    private MemberMusicId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("registeredMusicId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_music_id")
    private RegisteredMusic registeredMusic;

    @MapsId("musicId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;
}
