package com.wangqin.globalshop.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author biscuit
 * @data 2018/07/12
 * 帮助没有交给spring管理的类读取配置文件
 */
public class ConfigUtil {
    private static String imgUrl;

    static {
        InputStream in = null;
        try {
            Properties pt = new Properties();
            in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
            pt.load(in);
            imgUrl = pt.getProperty("imgUrl");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getImgUrlWhenNotFound() {
        return imgUrl;
    }
}
