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
public class MusicHistory {

    @EmbeddedId
    private MemberMusicId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("registeredMusicId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_music_id")
    private RegisteredMusic registeredMusic;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createTime;

    @Builder
    public MusicHistory(MemberMusicId id, Member member, RegisteredMusic registeredMusic) {
        this.id = id;
        this.member = member;
        this.registeredMusic = registeredMusic;
    }

    public void updateCreateTime(LocalDateTime createTime){
        this.createTime = createTime;
    }
}
