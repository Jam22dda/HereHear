package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long occasionCode;

    @Column(length = 50, nullable = false)
    private String occasionName;
}
