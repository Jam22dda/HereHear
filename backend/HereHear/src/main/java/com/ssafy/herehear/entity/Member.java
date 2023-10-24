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

    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    // 동적으로 변경이 가능할 수 있으니 updateNickname 메서드를 만들어서 setter 역할을 준다 (@Setter 사용 지양)
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}