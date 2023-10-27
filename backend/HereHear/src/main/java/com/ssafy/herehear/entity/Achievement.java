package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achievementId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_code")
    private BadgeCode badgeCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_code")
    private TitleCode titleCode;

    @Column(columnDefinition = "TEXT")
    private String mission;

    @Builder
    public Achievement(BadgeCode badgeCode, TitleCode titleCode, String mission) {
        this.badgeCode = badgeCode;
        this.titleCode = titleCode;
        this.mission = mission;
    }

}
