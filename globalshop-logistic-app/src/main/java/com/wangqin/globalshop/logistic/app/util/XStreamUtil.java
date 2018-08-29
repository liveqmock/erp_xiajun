package com.wangqin.globalshop.logistic.app.util;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * XStream 对应的工具类
 *
 * @author angus
 * @date 2018/8/23
 */
public class XStreamUtil {

    /**
     * 将 xml 数据转换为对应的实体对象
     *
     * @param xmlStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T toBean(String xmlStr, Class<T> cls) {
        try {
            XStream xstream = new XStream();
            // 对指定的类使用 Annotations 进行序列化
            xstream.processAnnotations(cls);
            // spring-boot-devtools 热加载的问题，会导致 cls 由不同类加载器加载
            // 而 XStream 需要直接进行序列化和反序列化，故而不能直接进行强转
            Object obj = xstream.fromXML(xmlStr);
            // 使用 json 作为中转
            Gson gson = new Gson();
            String json = gson.toJson(obj);

            return gson.fromJson(json, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
