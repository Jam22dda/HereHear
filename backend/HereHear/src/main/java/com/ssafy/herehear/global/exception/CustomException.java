package com.ssafy.herehear.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends  RuntimeException{

    ExceptionStatus exceptionStatus;
}
