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
public class WearOsPersonalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personalCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @CreatedDate
    private LocalDateTime lastModifiedDate;

    @Builder
    public WearOsPersonalCode(String personalCode, Member member, LocalDateTime lastModifiedDate) {
        this.personalCode = personalCode;
        this.member = member;
        this.lastModifiedDate = lastModifiedDate;
    }

    public void updatePersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public void updateLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
