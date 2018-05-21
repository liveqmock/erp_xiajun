package com.wangqin.globalshop.biz1.api.dto.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pw
 * @date 2016-12-21
 */
@Data
public class BaseResp<T> implements Serializable {
    private static final long serialVersionUID = -2601525456949506425L;
    private T data;
    private String status;
    private String message;
    public BaseResp(){}

    public  static BaseResp create(Object data, String status, String message){
        BaseResp resp = new BaseResp();
        resp.setData(data);
        resp.setStatus(status);
        resp.setMessage(message);
        return resp;
    }
    public  static BaseResp createSuccess(Object data){
        BaseResp resp = new BaseResp();
        resp.setData(data);
        resp.setStatus(Status.S.name());
        resp.setMessage(Status.S.desc);
        return resp;
    }
    public  static BaseResp createFailure(String message){
        BaseResp resp = new BaseResp();
        resp.setStatus(Status.F.name());
        resp.setMessage(message);
        return resp;
    }
    public BaseResp isSuccess(){
        setStatus(Status.S.name());
        setMessage(Status.S.desc);
        return this;
    }
    private enum Status{
        S("成功"),F("失败"),U("未知");
        private String desc;
        Status(String desc) {
            this.desc =desc;
        }
    }
}
