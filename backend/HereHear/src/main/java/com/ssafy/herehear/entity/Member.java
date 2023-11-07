package com.ssafy.herehear.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String nickname;

    @CreatedDate
    private LocalDateTime registDate;

    private LocalDateTime removeDate;

//    @ColumnDefault("USER")
    private String role;

    @Column(nullable = false)
    private String provider;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_character_id")
    private ProfileCharacter profileCharacter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;


    @Builder
    public Member(String email, String nickname, String provider) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
    }

    public void updateCharacter(ProfileCharacter profileCharacter) {
        this.profileCharacter = profileCharacter;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public void deleteMember() {
        this.removeDate = LocalDateTime.now();
    }

    public void resignUp() {
        this.registDate = LocalDateTime.now();
        this.removeDate = null;
    }

}