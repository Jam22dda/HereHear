package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Transactional
public class RegisteredMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registeredMusicId;

    @Column(nullable = false)
    private Double lng;

    @Column(nullable = false)
    private Double lat;

    @Column(length = 100)
    private String comment;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime registerTime = LocalDateTime.now();

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
