package com.ssafy.herehear.music.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateMusicIdReqDto {
    @NotNull(message = "등록된 음악 id 값은 필수값 입니다.")
    Long registeredMusicId;
}
