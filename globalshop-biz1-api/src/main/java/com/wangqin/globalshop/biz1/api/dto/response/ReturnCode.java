package com.wangqin.globalshop.biz1.api.dto.response;

import lombok.Getter;

@Getter
public enum ReturnCode implements BaseReturnType {

    SUCCESS("100", "接口返回正常"),
    ILLEGAL_ARG_EXCEPTION("400", "请求参数异常"),


    REMOTE_EXCEPTION("998", "远程调用系统异常, {0}"),
    SYSTEM_EXCEPTION("999", "系统异常");
    private String code;
    private String message;

    ReturnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }



}
