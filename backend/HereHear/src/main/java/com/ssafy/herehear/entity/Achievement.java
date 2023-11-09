package com.ssafy.herehear.entity;

import com.ssafy.herehear.entity.type.AchievementCategoryType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long achievementId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "badge_code")
    private BadgeCode badgeCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "title_code")
    private TitleCode titleCode;

    @Column(columnDefinition = "TEXT")
    private String mission;

    private int count;

    @Enumerated(EnumType.STRING)
    private AchievementCategoryType category;

    @Builder
    public Achievement(BadgeCode badgeCode, TitleCode titleCode, String mission, int count, AchievementCategoryType category) {
        this.badgeCode = badgeCode;
        this.titleCode = titleCode;
        this.mission = mission;
        this.count = count;
        this.category = category;
    }

}
