package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.transaction.annotation.Transactional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
public class MusicOccasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long music_occasion_id;

    @ManyToOne
    @JoinColumn(name = "occasion_code")
    private Occasion occasion;

    @ManyToOne
    @JoinColumn(name = "registered_music_id")
    private RegisteredMusic registeredMusic;

}
