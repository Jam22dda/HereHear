package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MusicOccasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicOccasionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occasion_code")
    private Occasion occasion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_music_id")
    private RegisteredMusic registeredMusic;

}
