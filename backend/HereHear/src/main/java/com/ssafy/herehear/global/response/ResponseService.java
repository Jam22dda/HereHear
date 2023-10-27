package com.ssafy.herehear.global.response;

import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public CommonResponse successResponse(ResponseStatus responseStatus){
        CommonResponse successResponse = new CommonResponse();
        successResponse.setCode(responseStatus.getCode());
        successResponse.setMessage(responseStatus.getMessage());
        return successResponse;
    }

    public <T> DataResponse<T> successDataResponse(ResponseStatus responseStatus, T data){
        DataResponse dataResponse = new DataResponse<>();
        dataResponse.setCode(responseStatus.getCode());
        dataResponse.setMessage(responseStatus.getMessage());
        dataResponse.setData(data);
        return dataResponse;
    }
}
