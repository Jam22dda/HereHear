package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadList {

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
