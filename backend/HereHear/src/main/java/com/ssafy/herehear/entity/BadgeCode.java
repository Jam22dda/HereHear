package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BadgeCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeCode;

    @Column(length = 50, nullable = false, unique = true)
    private String badgeName;

    @Column(length = 255)
    private String badgeImg;

    @Builder
    public BadgeCode(Long badgeCode, String badgeName, String badgeImg) {
        this.badgeCode = badgeCode;
        this.badgeName = badgeName;
        this.badgeImg = badgeImg;
    }
}
