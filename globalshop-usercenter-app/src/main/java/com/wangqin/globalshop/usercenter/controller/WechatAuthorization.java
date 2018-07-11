package com.wangqin.globalshop.usercenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.WxPay.PayUtil;
import com.wangqin.globalshop.usercenter.wechat_sdk.AesException;
import com.wangqin.globalshop.usercenter.wechat_sdk.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author biscuit
 * @data 2018/07/10
 * 第三方平台授权controller
 */
@RestController
@RequestMapping("/account")
public class WechatAuthorization {
    @Autowired
    private InventoryOnWarehouseMapperExt mapper;
    @Resource
    private Cache loginCache;

    @RequestMapping("/authorization")
    public void getComponentVerifyTicket(@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
                                         @RequestParam("msg_signature") String msgSignature, @RequestBody String postData) {
        WXBizMsgCrypt pc;
        try {
            pc = new WXBizMsgCrypt("FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc", "FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc7wud647ryu1", "wx43020a9d04042b56");

            String result2 = pc.DecryptMsg(msgSignature, timestamp, nonce, postData);
            System.out.println("解密后明文:" + result2);
        } catch (AesException e) {
            e.printStackTrace();
        }

    }

    //todo
    private String componentVerifyTicket = "ticket@@@g5bKJ_d9VllrbU_6QWgCAmmR3FS32XwQ2Hyiiyks-Zr0IVvzT5S-o0hcjFodCOSfgTjlhvohINrNQvvfTf-MXw";
    private String componentAppid = "wxe25c15397f0ec710";
    private String componentAppsecret = "8eb667a448cb3226d57878acfaca84a0";

    @RequestMapping("/getHtml")
    public String getHtml() {
        String re_url = null;
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + getToken();
            //language=JSON
            String param = "{\"component_appid\":\"" + componentAppid + "\"}";
            String post = PayUtil.httpRequest(url, "POST", param);
            JSONObject object = JSON.parseObject(post);
            /**预授权码*/
            String preAuthCode = object.getString("pre_auth_code");

            re_url = URLEncoder.encode("http://test.buyer007.cn", "UTF-8");

            String reUrl = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + componentAppid + "&pre_auth_code=" + preAuthCode + "&redirect_uri=" + re_url + "&auth_type=2";
            return "<html><head><title>Title</title></head><body><a href=\"" + reUrl + "\">授权小程序</a></body>\n" +
                    "</html>";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //todo
        return "";


    }


    private String getToken() {
        String componentAccessToken = (String)loginCache.get("component_access_token");
//        String componentVerifyTicket = ops.get("component_verify_ticket");
        /**判断数据库中是否存在component_access_token*/
        if (!EasyUtil.isStringEmpty(componentAccessToken)) {
            /**如果存在，直接返回token的值*/
            return componentAccessToken;
        }
//        /**ticket若不存在，返回错误信息*/
//        if(componentVerifyTicket.isEmpty()){
//            /**错误信息*/
//            return "如果出现这条消息，说明ticket以及token的过期处理存在BUG";
//        }
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
        //language=JSON
        String param = "{\"component_appid\":\"" + componentAppid + "\",\"component_appsecret\": \"" + componentAppsecret + "\",\"component_verify_ticket\":\""+componentVerifyTicket +"\"}";
        String s = PayUtil.httpRequest(url, "POST", param);
        JSONObject obj = JSON.parseObject(s);
        componentAccessToken = obj.getString("component_access_token");
        System.out.println(componentAccessToken);
        /**在返回结果中获取token*/
        /**保存token，并设置有效时间*/

        loginCache.putEx("component_access_token", componentAccessToken, 7200000L);
        return componentAccessToken;

    }

    public static void main(String[] args) throws AesException {

        String timestamp = "1531275378";
        String nonce = "1771175169";
        String msgSignature = "77eefd91c94da216be112f7c6fdf9b71a05eb6f6";
        String postData = "<xml><AppId><![CDATA[wxe25c15397f0ec710]]></AppId><Encrypt><![CDATA[M8mvp7POuAn4KlziEJqqL3bpdiBIQojsfie6/ROE+amI7NAHkTpKgdDNBwV3SlF2e5BAId3V6eleCZBMDhYnSF2aGlW+NsZIE7ayJV7TyEeybYN5MT6gXEsHNY/+twXVWrxjiVP4pv0dIdsooCOBRh7ktq6XDqdc270zRv3PcEA2fHO5mzNE8eDeMuIDiu3twIHgLfvIXp42PcU7FRub8QchZ4Yq2oKWzbrL4Uqn8W1z9VmMUcFuv/iUkGxqviol1YjmphC1lAT6JhIc1GWYyyIv5vTbA/43mi2X5UtClkGD8TGIRRSHHzPmQPPFTCLfjhbfMTwI3x9baL7YLFUUACa6hht+OdxdaLts8OnMO1mKZ/1yW53W4F1+S3FOAe7mtDW4+DcV5SeEb5L0/YjZpp2Yqazx5eS99CGJgCXDG+pCZZUMdJ4fgCodtv+oJYRa+38UZa+nHnj+Z1vhdkMu5A==]]></Encrypt></xml>";
        postData = postData.replace("AppId", "ToUserName");
        WXBizMsgCrypt pc = new WXBizMsgCrypt("FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc", "FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc7wud647ryu1", "wxe25c15397f0ec710");
        String result2 = pc.DecryptMsg(msgSignature, timestamp, nonce, postData);
        System.out.println("解密后明文: " + result2);

    }

}
