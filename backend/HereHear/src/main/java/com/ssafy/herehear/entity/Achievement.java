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
    @JoinColumn(name = "border_code")
    private BorderCode borderCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_code")
    private TitleCode titleCode;

    @Column(columnDefinition = "TEXT")
    private String mission;

    @Column(length = 255)
    private String icon;

    @Builder
    public Achievement(BorderCode borderCode, TitleCode titleCode, String mission, String icon) {
        this.borderCode = borderCode;
        this.titleCode = titleCode;
        this.mission = mission;
        this.icon = icon;
    }

}
