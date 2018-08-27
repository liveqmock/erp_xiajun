package com.wangqin.globalshop.logistic.app.util;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * @author angus
 * @date 2018/8/23
 */
public class XStreamUtil {

    public static <T> T toBean(String xmlStr, Class<T> cls) {
        try {
            XStream xstream = new XStream();
            // 对指定的类使用 Annotations 进行序列化
            xstream.processAnnotations(cls);
            // SpringBoot DevTools 热加载的问题，会导致 cls 由不同类加载加载
            // 而 XStream 需要直接进行序列化和反序列化，故而不能直接强转结果
            Object obj = xstream.fromXML(xmlStr);
            Gson gson = new Gson();
            String json = gson.toJson(obj);
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
