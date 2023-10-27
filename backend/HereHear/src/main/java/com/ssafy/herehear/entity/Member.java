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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_code")
    private TitleCode titleCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "border_code")
    private BorderCode borderCode;

    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateTitleCode(TitleCode titleCode) {
        this.titleCode = titleCode;
    }

    public void updateBorderCode(BorderCode borderCode) {
        this.borderCode = borderCode;
    }

}