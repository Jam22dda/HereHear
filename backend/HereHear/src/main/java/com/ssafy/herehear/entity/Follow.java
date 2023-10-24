package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @Column(nullable = false)
    private Long followMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Follow(Long followMemberId, Member member) {
        this.followMemberId = followMemberId;
        this.member = member;
    }

    public Follow(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
