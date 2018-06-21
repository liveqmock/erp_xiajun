package com.wangqin.globalshop.common.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个包含了微信相关的操作：获取accesstoken、用户信息、地理位置、支付、二维码扫描等等
 * 
 * @author Sivan
 */
public class WechatHelper {

    protected static Logger     wxLog       = LoggerFactory.getLogger("Wechat");
    @Value("#{sys.WXAPPID}")
    private static String WXAPPID;
    @Value("#{sys.WXAPPSECRET}")
    private static String WXAPPSECRET;

    /**
     * 创建带场景的二维码（临时，过期时间默认30天）
     * 
     * @param accessToken
     * @param sceneId
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String createTicketWithScene(String accessToken, int sceneId) {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        Map<String, Object> sceneMap = new HashMap<String, Object>();
        sceneMap.put("scene_id", sceneId);
        Map<String, Object> actionInfoMap = new HashMap<String, Object>();
        actionInfoMap.put("scene", sceneMap);
        Map<String, Object> ticketDataMap = new HashMap<String, Object>();
        ticketDataMap.put("expire_seconds", 2592000);
        ticketDataMap.put("action_name", "QR_LIMIT_SCENE");
        ticketDataMap.put("action_info", actionInfoMap);

        wxLog.info("qrcode create request data: " + ticketDataMap);

        String response = HttpClientUtil.post(url, JSONUtil.object2JSON(ticketDataMap));
        wxLog.info("qrcode create response: " + response);
        HashMap<String, String> result = (HashMap<String, String>) JSON.parseObject(response, HashMap.class);
        String ticket = (String) result.get("ticket");
        return ticket;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> getUserInfo(String accessToken, String openid) {
        String requestUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?lang=zh_CN&access_token=" + accessToken
                                    + "&openid=" + openid;
        String resultString = HttpClientUtil.get(requestUserInfoUrl);
        wxLog.info(resultString);
        Map<String, Object> userInfo = (HashMap<String, Object>) JSON.parseObject(resultString, HashMap.class);
        return userInfo;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> createMenu(String accessToken, String menuContent) {
        String createMenuurl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        String menuCreateRes = HttpClientUtil.post(createMenuurl, menuContent);
        wxLog.info("menuCreateRes :" + menuCreateRes);
        Map<String, Object> result = (Map<String, Object>) JSON.parse(menuCreateRes);
        return result;
    }

    public static String createRedirectUrl(String appId, String redirectUrl, String scope, String state) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
        builder.append("appid=" + appId);
        String encodedUrl = null;
        try {
            encodedUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        builder.append("&redirect_uri=" + encodedUrl);
        builder.append("&response_type=code");
        builder.append("&scope=" + scope);
        if (state != null) {
            builder.append("&state=" + state);
        }
        builder.append("#wechat_redirect");
        wxLog.info(builder.toString());
        return builder.toString();
    }

    /**
     * 微信小程序登录，code换取session_key和openid
     * 
     * @param code
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xcxLogin(String code) {
        String appId = WXAPPID;
        String secret = WXAPPSECRET;

        StringBuilder buider = new StringBuilder();
        buider.append("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId);
        buider.append("&secret=" + secret);
        buider.append("&js_code=" + code);
        buider.append("&grant_type=authorization_code");
        String result = HttpClientUtil.get(buider.toString());
        if (result == null) {
            return null;
        }
        wxLog.info("xcx login result:" + result);
        Map<String, String> resultOject = JSONUtil.json2Object(result, HashMap.class);
        if (resultOject.get("errcode") == null) {
            return resultOject;
        }
        return null;
    }

    /**
     * 微信小程序登录，code换取session_key和openid
     * 
     * @param code
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xcxLogin(String code, String appId, String secret) {
        StringBuilder buider = new StringBuilder();
        buider.append("https://api.weixin.qq.com/sns/jscode2session?appid=" + appId);
        buider.append("&secret=" + secret);
        buider.append("&js_code=" + code);
        buider.append("&grant_type=authorization_code");
        String result = HttpClientUtil.get(buider.toString());
        if (result == null) {
            return null;
        }
        wxLog.info("xcx login result:" + result);
        Map<String, String> resultOject = JSONUtil.json2Object(result, HashMap.class);
        if (resultOject.get("errcode") == null) {
            return resultOject;
        }
        return null;
    }

    /**
     * 生成小程序码
     * 
     * @param accessToken
     * @param scene
     * @return
     */
    public static byte[] createXcxQRCodeWithScene(String accessToken, String page, String scene, Integer width,
                                                  Map<String, String> lineColor) {
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
        Map<String, Object> ticketDataMap = new HashMap<String, Object>();
        ticketDataMap.put("scene", scene);
        if (width != null) {
            ticketDataMap.put("width", width);
        }
        ticketDataMap.put("page", page);
        if (lineColor.keySet().size() > 0) {
            ticketDataMap.put("line_color", lineColor);
        }
        wxLog.info("qrcode create request data: " + ticketDataMap);
        byte[] responseBytes = HttpClientUtil.postWithResponseByte(url, JSONUtil.object2JSON(ticketDataMap), null);
        wxLog.info("qrcode create response: " + responseBytes);
        return responseBytes;
    }
}
