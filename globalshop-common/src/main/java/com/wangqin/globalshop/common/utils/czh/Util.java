package com.wangqin.globalshop.common.utils.czh;

/**
 * @author biscuit
 * @data 2018/05/11
 */
public class Util {
    public static boolean isEmpty(String str){
        if (str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }
}
