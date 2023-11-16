package com.ssafy.herehear.music.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class AroundSearchReqDto {
    @NotNull(message = "경도는 필수로 입력해야 합니다")
    @DecimalMin(value = "-180.0", message = "경도는 [-180.0, 180.0] 범위에 있어야 합니다")
    @DecimalMax(value = "180.0", message = "경도는 [-180.0, 180.0] 범위에 있어야 합니다")
    private Double lng;

    @NotNull(message = "위도는 필수로 입력해야 합니다")
    @DecimalMin(value = "-90.0", message = "위도는 [-90.0, 90.0] 범위에 있어야 합니다")
    @DecimalMax(value = "90.0", message = "위도는 [-90.0, 90.0] 범위에 있어야 합니다")
    private Double lat;

    @Size(max = 3, message = "최대 3개까지의 상황태그만 검색이 가능합니다")
    private List<Long> occasions;

    @Size(max = 50, message = "최대 50글자까지 입력이 가능합니다")
    private String keyword;
}
