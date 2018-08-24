package com.wangqin.globalshop.item.app.feign;

public interface BaseReturnType {

    /**
     * 返回调用结果
     */
    String getCode();

    /**
     * 返回具体的处理消息
     */
    String getMessage();
}
