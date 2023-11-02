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
public class LocalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    private String originalFileName;

    private String savedFileName;

    private String savedFilePath;

    @Builder
    public LocalFile(String originalFileName, String savedFileName, String savedFilePath) {
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.savedFilePath = savedFilePath;
    }
}
