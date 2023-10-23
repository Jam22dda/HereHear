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
@IdClass(MemberMusicPK.class)
public class MemberReadList {

    @Id
    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @MapsId("registeredMusicId")
    @ManyToOne
    @JoinColumn(name = "registered_music_id")
    private RegisteredMusic registeredMusic;

}
