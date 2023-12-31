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
public class MemberMusicId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "registered_music_id")
    private Long registeredMusicId;

    @Builder
    public MemberMusicId(Long memberId, Long registeredMusicId) {
        this.memberId = memberId;
        this.registeredMusicId = registeredMusicId;
    }
}