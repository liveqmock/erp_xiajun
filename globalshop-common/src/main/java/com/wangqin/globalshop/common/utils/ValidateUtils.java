package com.wangqin.globalshop.common.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * @author sw
 * @date 2016-6-15
 */
public final class ValidateUtils {
    private ValidateUtils() {
    }

    /**
     * @param str
     * @param msg
     * @throws IllegalArgumentException
     */
    public static void fail4Null(final Object str, final String msg){
        if (str == null || StringUtils.isEmpty(String.valueOf(str))) {
            throw new IllegalArgumentException(msg + "不能是空");
        }
    }

    /**
     * @param str
     * @param msg
     * @throws IllegalArgumentException
     */
    public static void fail4Null2Msg(final Object str, final String msg) {
        if (str == null || StringUtils.isEmpty(String.valueOf(str))) {
            throw new IllegalArgumentException(msg);
        }
    }
}
