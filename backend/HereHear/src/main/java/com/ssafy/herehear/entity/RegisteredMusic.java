package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Column(nullable = false)
    private Double lng;

    @Column(nullable = false)
    private Double lat;

    @Column(length = 100)
    private String comment;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registerTime;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public RegisteredMusic(Double lng, Double lat, String comment) {
        this.lng = lng;
        this.lat = lat;
        this.comment = comment;
    }

    public RegisteredMusic(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
