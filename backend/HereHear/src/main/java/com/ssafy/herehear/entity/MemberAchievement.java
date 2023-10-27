package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class MemberAchievement {

    @EmbeddedId
    private MemberAchievementId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("achievementId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @CreatedDate
    private LocalDateTime clearTime;

    @Builder
    public MemberAchievement(MemberAchievementId id, Member member, Achievement achievement) {
        this.id = id;
        this.member = member;
        this.achievement = achievement;
    }
}
