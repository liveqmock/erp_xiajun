package com.wangqin.shopkeeper.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by Patrick on 19/12/2017.
 */
public class StringHelper {
    public static String json(Object o){
        return JSON.toJSONString(o);
    }
}
