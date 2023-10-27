package com.ssafy.herehear.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private double lng;
    private double lat;
    private LocalDateTime createTime;

    @Builder
    public LocationLog(Member member, double lng, double lat, LocalDateTime createTime) {
        this.member = member;
        this.lng = lng;
        this.lat = lat;
        this.createTime = createTime;
    }
}
