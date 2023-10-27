package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProfileCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileCharacterId;

    @Column
    private String characterName;

    @Column
    private String characterImage;
}
