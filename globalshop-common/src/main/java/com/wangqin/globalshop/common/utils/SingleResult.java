package com.wangqin.globalshop.common.utils;

/**
 * 单条结果
 * 
 * @author Robin 2014年7月14日 上午11:10:49
 */
public class SingleResult<T> extends SuccessResult {

    private T data = null;

    public SingleResult(T data){
        super();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
