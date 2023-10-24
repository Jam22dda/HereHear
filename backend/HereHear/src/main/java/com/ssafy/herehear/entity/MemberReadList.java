package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
@IdClass(MemberMusicId.class)
public class MemberReadList {

    @EmbeddedId
    private MemberMusicId id;

    @MapsId("occasionCode")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occasion_code")
    private Member member;

    @MapsId("registeredMusicId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_music_id")
    private RegisteredMusic registeredMusic;

}
