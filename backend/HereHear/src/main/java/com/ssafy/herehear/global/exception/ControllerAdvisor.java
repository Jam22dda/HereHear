package com.ssafy.herehear.global.exception;

import com.ssafy.herehear.global.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse customExceptionHandler(CustomException e) {
        CommonResponse response = new CommonResponse();
        response.setCode(e.getExceptionStatus().getCode());
        response.setMessage(e.getExceptionStatus().getMessage());
        e.printStackTrace();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();

        StringBuilder errorMessageBuilder = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errorMessageBuilder.append(errorMessage).append(", ");
        });
        if (errorMessageBuilder.length() > 0) errorMessageBuilder.setLength(errorMessageBuilder.length() - 2);

        return new CommonResponse("400", errorMessageBuilder.toString());
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse exceptionHandler(Exception e) {
        // 디버깅을 위한 로그 출력
        e.printStackTrace();
        log.info("exception: {}", e.getMessage());

        CommonResponse response = new CommonResponse();
        response.setCode(ExceptionStatus.EXCEPTION.getCode());
        response.setMessage(ExceptionStatus.EXCEPTION.getMessage());
        return response;
    }
}
