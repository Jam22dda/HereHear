package com.ssafy.herehear.global.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.herehear.global.response.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(401);
        ObjectMapper objectMapper = new ObjectMapper();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(ExceptionStatus.TOKEN_EXPIRED.getCode());
        commonResponse.setMessage(ExceptionStatus.TOKEN_EXPIRED.getMessage());
        objectMapper.writeValue(response.getWriter(), commonResponse);
    }
}
