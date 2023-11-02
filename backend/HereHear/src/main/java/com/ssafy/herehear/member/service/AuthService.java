package com.ssafy.herehear.member.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    String refresh(HttpServletRequest request);
}
