package com.wangqin.globalshop.common.utils;

import com.wangqin.globalshop.common.result.RetCode;

/**
 * 成功结果
 * 
 * @author Robin 2014年7月14日 下午1:01:29
 */
public class SuccessResult extends Result {

    public SuccessResult(){
        this.setRetCode(RetCode.SUCCESS);
    }
}
