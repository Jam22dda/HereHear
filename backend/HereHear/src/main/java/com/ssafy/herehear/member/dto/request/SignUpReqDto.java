package com.ssafy.herehear.member.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
public class SignUpReqDto {
    @NotNull(message = "회원 id 값은 필수값 입니다.")
    private Long memberId;

    @NotEmpty(message = "닉네임은 필수값 입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z가-힣]).{1,20}$",
            message = "최소 하나의 영문, 한글과 숫자가 포함된 20글자 이내로 작성해야 합니다.")
    @Size(max = 20, message = "최대 20글짜까지 입력이 가능합니다.")
    private String nickname;

    @NotNull(message = "초기 프로필 캐릭터는 필수로 선택해야 합니다")
    private Long profileCharacterCode;
}
