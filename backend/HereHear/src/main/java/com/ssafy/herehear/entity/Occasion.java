package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long occasionCode;

    @Column(length = 50, nullable = false)
    private String occasionName;

}
