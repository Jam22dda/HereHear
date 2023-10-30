package com.ssafy.herehear.admin.dto;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.RegisteredMusic;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDto {

    private Long reportId;
    private String content;
    private LocalDateTime createTime;
    private Long memberId;
    private Long registeredMusicId;

}
