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

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false, unique = true)
    private String nickname;

    @CreatedDate
    private LocalDateTime registDate;

    private LocalDateTime removeDate;

    private String role;
    private String provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_character_id")
    private ProfileCharacter profileCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;


    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void updateCharacter(ProfileCharacter profileCharacter) {
        this.profileCharacter = profileCharacter;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void deleteMember() {
        this.removeDate = LocalDateTime.now();
    }

    public void resignUp() {
        this.registDate = LocalDateTime.now();
        this.removeDate = null;
    }

}