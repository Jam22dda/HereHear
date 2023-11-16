package com.ssafy.herehear.report.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReportReqDto {

    @NotEmpty(message = "신고 내용은 필수 입력값 입니다.")
    @Size(max = 3000, message = "최대 1000자까지 입력이 가능합니다")
    private String content;

    @NotNull(message = "신고 대상 음악id 는 필수 입력값 입니다")
    private Long registeredMusicId;

}
