package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class RegisteredMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registeredMusicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private Double lng;

    @Column(nullable = false)
    private Double lat;

    @Column(length = 100)
    private String comment;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(length = 100, nullable = false)
    private String subject;

    @Column(length = 50, nullable = false)
    private String singer;

    @Column(length = 255, nullable = false)
    private String albumImg;

    @Column(length = 10)
    private String releaseTime;

    private String spotifyUri;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createTime;

    @Builder
    public RegisteredMusic(Member member, Double lng, Double lat, String comment, String subject, String singer, String albumImg, String releaseTime) {
        this.member = member;
        this.lng = lng;
        this.lat = lat;
        this.comment = comment;
        this.subject = subject;
        this.singer = singer;
        this.albumImg = albumImg;
        this.releaseTime = releaseTime;
    }

    public void updateRegisteredMusic(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
