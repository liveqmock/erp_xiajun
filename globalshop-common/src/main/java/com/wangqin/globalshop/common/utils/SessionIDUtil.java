package com.wangqin.globalshop.common.utils;

public class SessionIDUtil {

    /**
     * 生成sessionID
     * 
     * @param request
     * @return
     */
    public static String generateSessionId() {
        return UUIDUtil.formatedUUID();
    }
    
}