package com.wangqin.globalshop.logistic.app.bean.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author angus
 * @date 2018/8/22
 */
@Data
@Builder
public class Response {
    private Boolean success;
    private String errorCode;
    private String errorMsg;
}
