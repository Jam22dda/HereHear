package com.ssafy.herehear.member.controller;

import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.member.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh")
    public DataResponse renewAccessToken(HttpServletRequest request) {
        String newAccessToken = authService.refresh(request);
        return new DataResponse("200", "토큰 발급이 완료되었습니다", newAccessToken);
    }

}
