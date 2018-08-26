package com.wangqin.globalshop.biz1.api.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class BaseResponseDto<T> {

    private T data;

    private String returnCode = ReturnCode.SUCCESS.getCode();

    private String message;

    public static BaseResponseDto<String> buildError(ApplicationException ae){
        BaseResponseDto<String> responseDto = new BaseResponseDto<>();
        responseDto.setReturnCode(ae.getType().getCode());
        responseDto.setMessage(ae.getMessage());
        return responseDto;
    }

    public static BaseResponseDto<String> buildError(BaseReturnType returnType, Object... args){
        return buildError(new ApplicationException(returnType, args));
    }

    public static BaseResponseDto<String> buildSystemError(){
        return buildError(ReturnCode.SYSTEM_EXCEPTION);
    }

}
