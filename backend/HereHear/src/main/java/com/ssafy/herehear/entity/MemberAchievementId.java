package com.ssafy.herehear.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class MemberAchievementId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "achievement_id")
    private Long achievementId;

    @Builder
    public MemberAchievementId(Long memberId, Long achievementId) {
        this.memberId = memberId;
        this.achievementId = achievementId;
    }

}
