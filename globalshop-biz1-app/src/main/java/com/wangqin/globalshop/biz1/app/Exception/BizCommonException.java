package com.wangqin.globalshop.biz1.app.exception;

/**
 * ERP通用异常
 *
 * @author zhulu
 */
public class BizCommonException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public BizCommonException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizCommonException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


}
