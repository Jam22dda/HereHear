package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TitleCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long titleCode;

    @Column(length = 50, nullable = false, unique = true)
    private String titleName;

}
