package com.wangqin.globalshop.logistic.app.util;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * XStream 工具类
 *
 * @author angus
 * @date 2018/8/23
 */
public class XStreamUtil {

    private static XStream xstream = new XStream();

    /**
     * 将实体对象序列化为 xml（XStream 会忽略为 null 的字段）
     *
     * @param obj 实体对象
     * @param cls 实体对象所属类型，用于指导 XStream 按类中属性注解进行序列化
     * @return xml
     */
    public static String toXml(Object obj, Class cls) {
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";

        // 对指定的类使用 Annotations 进行序列化
        xstream.processAnnotations(cls);
        String xml = xstream.toXML(obj);

        return xmlHeader + xml;
    }

    /**
     * 将 xml 反序列化为实体对象（xml 与 bean 中字段存在不对应、缺失、类型不匹配时会报异常）
     *
     * @param xml xml 格式数据
     * @param cls 待转对象所属类型，用于指导 XStream 按类中属性注解进行反序列化
     * @return bean
     */
    public static <T> T toBean(String xml, Class<T> cls) {
        try {
            // 对指定的类使用 Annotations 进行序列化
            xstream.processAnnotations(cls);
            /*
             * spring-boot-devtools 热加载的问题，会导致 cls 由不同类加载器加载
             * 而 XStream 需要直接进行序列化和反序列化，故而不能直接进行强转。
             * Object obj = (T)xstream.fromXML(xml); // 会抛出 ClassCastException
             */
            Object obj = xstream.fromXML(xml);
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
