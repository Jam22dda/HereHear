package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    @Column(length = 100, nullable = false, unique = true)
    private String subject;

    @Column(length = 50, nullable = false)
    private String singer;

    @Column(length = 255, nullable = false)
    private String albumImg;

    @Builder
    public Music(String subject, String singer, String albumImg) {
        this.subject = subject;
        this.singer = singer;
        this.albumImg = albumImg;
    }
}
