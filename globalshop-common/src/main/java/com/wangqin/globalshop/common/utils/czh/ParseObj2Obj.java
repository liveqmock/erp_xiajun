package com.wangqin.globalshop.common.utils.czh;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseObj2Obj {
    private static PropertyDescriptor[] srcBeans;
    private static PropertyDescriptor[] desBeans;

    /**
     * 当两个类所需要转化的字段名称不同的时候
     *
     * @param src     源数据
     * @param des     目标数据
     * @param sameOne 所有字段名不同，但需要转换的映射 Map<src,des> (可选参数)
     */
    public static void parseObj2Obj(Object src, Object des, Map<String, String>... sameOne) {
        String srcName;
        String desName;
        srcBeans = getPropertyDescriptor(src);
        desBeans = getPropertyDescriptor(des);
        try {
            for (PropertyDescriptor desBean : desBeans) {
                for (PropertyDescriptor srcBean : srcBeans) {
                    srcName = srcBean.getName();
                    desName = desBean.getName();
                    if (desName.equals(srcName) || checkStringInMap(srcName, desName, sameOne)) {
                        desBean.getWriteMethod().invoke(des, srcBean.getReadMethod().invoke(src));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 当两个类所需要转化的字段名称不同的时候
     *
     * @param src     源数据
     * @param des     目标数据
     */
    public static void parseObj2Obj(Object src, Object des) {
        String srcName;
        String desName;
        srcBeans = getPropertyDescriptor(src);
        desBeans = getPropertyDescriptor(des);
        try {
            for (PropertyDescriptor desBean : desBeans) {
                for (PropertyDescriptor srcBean : srcBeans) {
                    srcName = srcBean.getName();
                    desName = desBean.getName();
                    if (desName.equals(srcName)) {
                        desBean.getWriteMethod().invoke(des, srcBean.getReadMethod().invoke(src));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 当两个类即存在名称相同且不需要转换的字段，又存在名称不同，且需要转换的数据的时候
     *
     * @param src     源数据
     * @param des     目标数据
     * @param dif     字段名称相同但是不需要转换的字段名集合
     * @param sameOne 所有字段名不同，但需要转换的映射 Map<src,des> (可选参数)
     */
    public static void parseObj2Obj(Object src, Object des, List<String> dif, Map<String, String>... sameOne) {
        String srcName;
        String desName;
        srcBeans = getPropertyDescriptor(src);
        desBeans = getPropertyDescriptor(des);
        try {
            for (PropertyDescriptor desBean : desBeans) {
                for (PropertyDescriptor srcBean : srcBeans) {
                    srcName = srcBean.getName();
                    desName = desBean.getName();
                    if ((desName.equals(srcName) || checkStringInMap(srcName, desName, sameOne)) && !dif.contains(desName)) {
                        desBean.getWriteMethod().invoke(des, srcBean.getReadMethod().invoke(src));
                        break;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * parseObj2Obj(Object src, Object des, List<String> dif, Map<String, String>... sameOne)
     * 的增强方法
     *
     * @param src
     * @param des
     * @param strs 一个字符串类型的数组，将不需要转换的字段直接存入，需要转换的字段以key=value的形式传入，且src在前
     * @throws Exception
     */
    public static void parseObj2Obj(Object src, Object des, String... strs) {
        List<String> dif = new ArrayList<>();
        Map<String, String> sameOne = new HashMap<>();
        parseString(sameOne,dif,strs);
        parseObj2Obj(src, des, dif, sameOne);

    }
    /**
     * parseObj2Obj(Object src, Object des, List<String> dif, Map<String, String>... sameOne)
     * 的增强方法
     *
     * @param src
     * @param des
     * @param str 一个字符串类型，将不需要转换的字段直接存入，需要转换的字段以key=value的形式传入，且src在前
     * @throws Exception
     */
    public static void parseObj2Obj(Object src, Object des, String str) {
        List<String> dif = new ArrayList<>();
        Map<String, String> sameOne = new HashMap<>();
        String[] strs = str.split(",");
        parseString(sameOne,dif,strs);
        parseObj2Obj(src, des, dif, sameOne);
    }

    private static void parseString(Map<String, String> sameOne, List<String> dif, String[] strs) {
        for (String s : strs) {
            if (s.contains("=")){
                String[] sameStr = s.split("=");
                sameOne.put(sameStr[0], sameStr[1]);
            } else {
                dif.add(s);
            }
        }
    }

    /**
     * 判断两个字符串的映射是否属于这个集合
     *
     * @param srcName
     * @param desName
     * @param obj
     * @return
     */
    private static boolean checkStringInMap(String srcName, String desName, Map<String, String>... obj) {
        if (obj != null && obj.length > 0) {
            Map<String, String> map = obj[0];
            if (desName.equals(map.get(srcName))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过对象实体获取属性描述器
     *
     * @param obj 对象实体
     * @return 属性描述器
     */
    private static PropertyDescriptor[] getPropertyDescriptor(Object obj) {
        try {
            return Introspector.getBeanInfo(obj.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
