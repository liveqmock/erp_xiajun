package com.wangqin.globalshop.common.utils;

/**
 * 错误结果
 * 
 * @author Robin 2014年7月14日 上午11:19:36
 */
public class ErrorResult extends Result {

    private String errorMsg;

    public ErrorResult(String retCode){
        this.setRetCode(retCode);
    }

    public ErrorResult(String retCode, String errorMsg){
        this.setRetCode(retCode);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
