package com.wangqin.globalshop.common.utils;

import java.util.ResourceBundle;

/**
 * 读取系统配置信息
 * 
 * @author Robin 2014年7月15日 上午11:42:50
 */
public final class ResourceUtil {

    private static ResourceBundle system;
    static {
        try {
            system = ResourceBundle.getBundle("/config/application");
        } catch (Exception e) {
            System.out.println("application.properties Not Found,");
        }
    }

    /**
     * 读取application.properties文件里面的值
     * 
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String msg = null;
        try {
            msg = system.getString(key);
        } catch (Exception e) {
            System.out.println("Key['" + key + "'] Not Found in application.properties .");
        }
        return msg;
    }
}
