package com.wangqin.globalshop.common.base;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class BaseDto implements Serializable, Cloneable {
    private static final long         serialVersionUID = 1L;
    private static final ObjectMapper JSON             = new ObjectMapper();

    static {
        JSON.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        JSON.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSON.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        JSON.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        JSON.setSerializationInclusion(Include.NON_NULL);

        JSON.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public Object clone() {
        return SerializationUtils.clone(this);
    }

    /**
     * 转换成JSON格式的字符串
     */
    @Override
    public String toString() {
        try {
            return JSON.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 转换成JSON格式的字符串
     * 
     * @param object
     * @return
     */
    public static String toString(Object object) {
        try {
            return JSON.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 把JSON格式的字符串解析成普通对象。比如：<br/>
     * <code><blockquote>
     * UserDto dto = BaseDto.fromJson(str, UserDto.class);
     * </blockquote></code>
     * 
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return JSON.readValue(json, classOfT);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 把JSON格式的字符串解析成泛型类。比如：<br/>
     * <code><blockquote>
     * Map&lt;String, Object&gt; map = BaseDto.fromJson(str, new TypeReference&lt;Map&lt;String, Object&gt;&gt;() {});
     * </blockquote></code>
     * 
     * @param json
     * @param typeOfT
     * @return
     */
    public static <T> T fromJson(String json, TypeReference<T> typeOfT) {
        try {
            return JSON.readValue(json, typeOfT);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
