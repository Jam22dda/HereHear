package com.ssafy.herehear.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataResponse<T> extends CommonResponse {

    private T data;

    public DataResponse(String code, String message, T data) {
        super(code, message);
        this.data = data;
    }

}
