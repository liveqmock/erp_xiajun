package com.wangqin.globalshop.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pw
 * @date 2016-12-21
 */
@Data
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = -2601525456949506425L;
    private Object data;
    private String status;
    private String message;
    public ResultDTO(){}

    public  static ResultDTO create(Object data, String status, String message){
        ResultDTO restfulDTO = new ResultDTO();
        restfulDTO.setData(data);
        restfulDTO.setStatus(status);
        restfulDTO.setMessage(message);
        return restfulDTO;
    }
    public  static ResultDTO createSuccess(Object data){
        ResultDTO restfulDTO = new ResultDTO();
        restfulDTO.setData(data);
        restfulDTO.setStatus(Status.S.name());
        restfulDTO.setMessage(Status.S.desc);
        return restfulDTO;
    }
    public  static ResultDTO createFailure(String message){
        ResultDTO restfulDTO = new ResultDTO();
        restfulDTO.setStatus(Status.F.name());
        restfulDTO.setMessage(message);
        return restfulDTO;
    }
    public ResultDTO isSuccess(){
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
