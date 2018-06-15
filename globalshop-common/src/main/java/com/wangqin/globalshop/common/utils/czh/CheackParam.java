package com.wangqin.globalshop.common.utils.czh;

import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;


/**
 * @author biscuit
 * @data 2018/05/14
 */
@ToString
@Getter
@Setter
public class CheackParam<T> {
    private CheackParam(){}
    public CheackParam(T obj){
    }

    /**
     * 判断时候含有必有的参数，同时会根据这些参数进行签名，比对前端传过来的签名
     * @param data 前端界面传过来的json字符串的对象
     * @param param 必传参数的名字的数组
     */
    public void cheackSign(T data, String... param) {
        Map<String, Object> bean = BeanMapUtil.beanToMap(data);
//        Map<String, String> map = new HashMap<>();
        /*判断前端传输过来的json字符串是否成功转换为对象*/
        if (bean == null){
            throw new ErpCommonException("获取不到参数");
        }
        if (!bean.containsKey("sign")) {
            throw new ErpCommonException("参数不全:缺少签名");
        }
        String sign = (String) bean.get("sign");
        if (Util.isEmpty(sign)) {
            throw new ErpCommonException("签名为空");
        }
        for (String s : param) {
            if (!bean.containsKey(s)) {
                throw new ErpCommonException("参数不全:缺少" + s);
            }
//            map.put(s, (String) bean.get(s));

        }
//        String newSign  = null;
//        if (!sign.equals(newSign)) {
//            throw new ErpCommonException( "签名错误");
//        }

    }

    /**
     * 判断时候含有必有的参数，同时会根据这些参数进行签名，比对前端传过来的签名
     * @param data 前端界面传过来的json字符串的对象
     * @param param 必传参数的名字的数组
     */
    public static void cheack(JSONObject data, String... param) {
        Map<String, Object> bean = BeanMapUtil.beanToMap(data);
        /*判断前端传输过来的json字符串是否成功转换为对象*/
        if (bean == null){
            throw new ErpCommonException("获取不到参数");
        }
        for (String s : param) {
            if (!bean.containsKey(s)) {
                throw new ErpCommonException("参数不全:缺少" + s);
            }
        }
    }
}
