package com.ssafy.herehear.entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class MemberMusicPK implements Serializable {
    private Member member;
    private RegisteredMusic registeredMusic;
}