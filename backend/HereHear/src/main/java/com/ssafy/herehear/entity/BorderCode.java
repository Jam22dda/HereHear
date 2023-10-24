package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorderCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borderCode;

    @Column(length = 50, nullable = false, unique = true)
    private String borderName;

    @Column(length = 255)
    private String borderImg;

    @Builder
    public BorderCode(String borderName, String borderImg) {
        this.borderName = borderName;
        this.borderImg = borderImg;
    }

}
