package com.ssafy.herehear.global.exception;

import com.ssafy.herehear.global.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse customExceptionHandler(CustomException e){
        CommonResponse response = new CommonResponse();
        response.setCode(e.getExceptionStatus().getCode());
        response.setMessage(e.getExceptionStatus().getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse exceptionHandler(Exception e) {
        CommonResponse response = new CommonResponse();
        response.setCode(ExceptionStatus.EXCEPTION.getCode());
        response.setMessage(ExceptionStatus.EXCEPTION.getMessage());
        return response;
    }
}
