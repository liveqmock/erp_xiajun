package com.wangqin.globalshop.common.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;

/**
 * @author  patrick
 */
public class LogWorker {
    public static final String PREFIX_LOG = "--->";
    public static final String PREFIX_LOG_ST = "===>";
    public static final String PREFIX_LOG_END = "<===";
    public static final String SEPARATOR ="|";

    public static void log(Logger log, String logTopic,String expStr,Object... objects){
        String expression = new StringBuilder(PREFIX_LOG).append(SEPARATOR)
                .append(logTopic).append(SEPARATOR).append(expStr).toString();
        log.info(expression,objects);
    }
    public static void logStart(Logger log,  String logTopic,String expStr,Object... objects) {
        String expression = new StringBuilder(PREFIX_LOG_ST).append(SEPARATOR)
                .append(logTopic).append(SEPARATOR).append(expStr).toString();
        log.info(expression, objects);
    }
    public static void logEnd(Logger log,  String logTopic,String expStr,Object... objects){
        String expression = new StringBuilder(PREFIX_LOG_END).append(SEPARATOR)
                .append(logTopic).append(SEPARATOR).append(expStr).toString();
        log.info(expression,objects);
    }
    public static String json(Object o){
        return JSON.toJSONString(o);
    }
}
