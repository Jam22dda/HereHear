package com.ssafy.herehear.music.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class RegisterMusicReqDto {
    @NotEmpty(message = "경도는 필수로 입력해야 합니다")
    @DecimalMin(value = "-180.0", message = "경도는 [-180.0, 180.0] 범위에 있어야 합니다")
    @DecimalMax(value = "180.0", message = "경도는 [-180.0, 180.0] 범위에 있어야 합니다")
    private Double lng;

    @NotEmpty(message = "위도는 필수로 입력해야 합니다")
    @DecimalMin(value = "-90.0", message = "경도는 [-90.0, 90.0] 범위에 있어야 합니다")
    @DecimalMax(value = "90.0", message = "경도는 [-90.0, 90.0] 범위에 있어야 합니다")
    private Double lat;

    @Size(max = 100, message = "최대 100글짜까지 코멘트를 작성할 수 있습니다")
    private String comment;

    @NotEmpty(message = "노래 제목은 필수값입니다")
    @Size(max = 100, message = "최대 100글자까지 입력이 가능합니다.")
    private String subject;

    @NotEmpty(message = "가수 이름은 필수값입니다.")
    @Size(max = 50, message = "최대 50글자까지 입력이 가능합니다")
    private String singer;

    @NotEmpty(message = "앨범 자켓 이미지는 필수 입력값입니다")
    @Size(max = 255, message = "최대 255자까지 입력이 가능합니다")
    private String albumImg;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "yyyy-MM-dd 의 날짜 형태로 입력해야 합니다")
    private String releaseTime;

    @Size(max = 3, message = "최대 3개까지의 상황태그만 등록이 가능합니다")
    private List<Long> musicOccasionIds;
}
