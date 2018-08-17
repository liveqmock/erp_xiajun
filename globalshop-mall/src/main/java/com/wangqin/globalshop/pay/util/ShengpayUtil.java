package com.wangqin.globalshop.pay.util;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.wangqin.globalshop.pay.service.ShengpayService.MD5_KEY;

/**
 * 对接盛付通需要的工具类
 *
 * @author angus
 * @date 2018/8/14
 */
public class ShengpayUtil {
    private static Logger logger = LoggerFactory.getLogger(ShengpayUtil.class);

    /**
     * 生成加密信息串 signMsg <br>
     * 计算公式：signMsg = md5(requestBody + md5key).toUpperCase()
     *
     * @param requestBodyObj 待放入请求体的对象
     * @return signMsg
     */
    public static String getSignMsgFromReq(Object requestBodyObj) {

        String requestBody = new Gson().toJson(requestBodyObj);
        logger.debug("requestBody: {}", requestBody);

        String signMsg = DigestUtils.md5Hex(requestBody + MD5_KEY).toUpperCase();
        logger.debug("signMsg: {}", signMsg);

        return signMsg;
    }

    public static String getSignMsgFromRes(String responseStr) {
        String signMsg = DigestUtils.md5Hex(responseStr + MD5_KEY).toUpperCase();
        logger.debug("signMsg: {}", signMsg);

        return signMsg;
    }
}
