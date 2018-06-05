package com.wangqin.globalshop.common.utils;

/**
 * 响应结果类
 * 
 * @author Robin 2014年7月14日 上午11:09:48
 */
public class Result {

    private String retCode;

    public Result(){
    }

    public Result(String retCode){
        this.retCode = retCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    /**
     * 返回json字符串，子类如果需要特别处理的话，可覆盖此方法
     * 
     * @return
     */
    public String toJSONString() {
        return JSONUtil.object2JSON(this);
    }
}
