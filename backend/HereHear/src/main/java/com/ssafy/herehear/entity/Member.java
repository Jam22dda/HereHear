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

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Follow> follows = new ArrayList<>();


    @Builder
    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname) {
    	this.nickname = nickname;
    }

}
