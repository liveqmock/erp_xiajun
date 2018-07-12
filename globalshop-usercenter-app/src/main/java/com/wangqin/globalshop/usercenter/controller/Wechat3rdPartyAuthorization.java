package com.wangqin.globalshop.usercenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.common.utils.WxPay.PayUtil;
import com.wangqin.globalshop.usercenter.service.IAppletConfigService;
import com.wangqin.globalshop.usercenter.wechat_sdk.AesException;
import com.wangqin.globalshop.usercenter.wechat_sdk.WXBizMsgCrypt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import static com.wangqin.globalshop.usercenter.constant.SysConstants.APPLET_TYPE;
import static com.wangqin.globalshop.usercenter.constant.SysConstants.PAY_STATUS_PLATFORM;

/**
 * @author biscuit
 * @data 2018/07/10
 * 第三方平台授权controller
 */
@RestController
@RequestMapping("/account")
public class Wechat3rdPartyAuthorization {
    @Resource
    private Cache loginCache;
    @Autowired
    private RedisTemplate cacheRedis;
    @Autowired
    private IAppletConfigService appletConfigServiceImplement;
    private static Logger log = LoggerFactory.getLogger("Wechat3rdPartyAuthorization");


    /**
     * 回调拿Ticket
     *
     * @param timestamp
     * @param nonce
     * @param msgSignature
     * @param postData
     */

    @RequestMapping("/authorization")
    public void getComponentVerifyTicket(@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
                                         @RequestParam("msg_signature") String msgSignature, @RequestBody String postData) {
        log.info("-----------------微信回调开始----------------");
        WXBizMsgCrypt pc;
        InputStream in = null;
        try {
            pc = new WXBizMsgCrypt("FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc", "FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc7wud647ryu1", componentAppid);
            log.info("微信回调:postData密文=" + postData);
            postData = postData.replace("AppId", "ToUserName");
            String result = pc.DecryptMsg(msgSignature, timestamp, nonce, postData);
            log.info("微信回调:postData明文=" + postData);
            in = new ByteArrayInputStream(result.getBytes());
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            HashMap<String, String> resultMap = new HashMap<>();
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            for (Element element : elementList) {
                resultMap.put(element.getName(), element.getText());
            }

            String componentVerifyTicket = resultMap.get("ComponentVerifyTicket");
            log.info("微信回调:component_verify_ticket=" + componentVerifyTicket);
            if (!StringUtils.isBlank(componentVerifyTicket)) {
                loginCache.put("componentVerifyTicket", componentVerifyTicket);
            }
            log.info("-----------------微信回调结束----------------------------");
        } catch (AesException | DocumentException e) {
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

    //todo
    private String componentAppid = "wxe25c15397f0ec710";
    private String componentAppsecret = "8eb667a448cb3226d57878acfaca84a0";

    @RequestMapping("/getHtml")
    public String getHtml() {
        String re_url = null;
        try {
            String token = getToken();
            log.info("获取小程序授权二维码:token===" + token);
            String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + token;
            //language=JSON
            String param = "{\"component_appid\":\"" + componentAppid + "\"}";
            String post = PayUtil.httpRequest(url, "POST", param);
            log.info("获取预授权码结果:" + post);
            JSONObject object = JSON.parseObject(post);
            /**预授权码*/
            String preAuthCode = object.getString("pre_auth_code");
            log.info("预授权码:" + preAuthCode);
            //todo 配置的是http://test.buyer007.cn/account/queryAuth 微信文档显示 该回调地址必须是http
            re_url = URLEncoder.encode("http://test.buyer007.cn/account/authcallback", "UTF-8");
            String reUrl = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + componentAppid + "&pre_auth_code=" + preAuthCode + "&redirect_uri=" + re_url + "&auth_type=2";
            //todo 有待优化
            String html = "<html><head><title>Title</title></head><body><a href=\"" + reUrl + "\">授权小程序</a></body></html>";
            log.info("html:" + html);
            return html;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //todo 有待优化
        return "<html><head><title>Title</title></head><body>获取授权页面失败</body></html>";


    }

    /**
     * 根据auth_code查询授权信息
     * 生成小程序的配置类对象
     * @param authCode  授权成功时获得的授权码
     * @param expiresIn 存活时间
     * @return
     */
    @RequestMapping(value = "/authcallback")
    public String queryAuth(@RequestParam("auth_code") String authCode, @RequestParam("expires_in") String expiresIn) {
        System.out.println("进入授权回调");
        System.out.println("auth_code=" + authCode);
        System.out.println("expires_in=" + expiresIn);
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + getToken();
        String param = "{\"component_appid\":\"" + componentAppid + "\",\"authorization_code\":\"" + authCode + "\"}";
        String s = PayUtil.httpRequest(url, "POST", param);
        System.out.println("----------------授权回调接口-------------");
        JSONObject o = JSON.parseObject(s);
        System.out.println(s);
        JSONObject info = o.getJSONObject("authorization_info");
        AppletConfigDO applet = getAppletDO(info,APPLET_TYPE);

        appletConfigServiceImplement.insert(applet);
        System.out.println("----------------授权回调接口-------------");
        return "success";
    }
    //todo
    @PostMapping("/...")
    public String initApplet(){
        //    设置小程序服务器域名
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(AppUtil.getLoginUserCompanyNo(), APPLET_TYPE);
        String accessToken = applet.getAuthorizerAccessToken();
        String url1 = "https://api.weixin.qq.com/wxa/modify_domain?access_token=" + accessToken;
        String param1 = "{\"action\":\"add\",\"requestdomain\":[\"https://test.buyer007.cn\"],\"wsrequestdomain\":[\"wss://test.buyer007.cn\"],\"uploaddomain\":[\"https://test.buyer007.cn\"], \"downloaddomain\":[\"https://test.buyer007.cn\"]}";
        String post1 = PayUtil.httpRequest(url1, "POST", param1);
        System.out.println(post1);

//        设置小程序业务域名
        String url2 = "https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=" + accessToken;
        //language=JSON
        String param2 = "{\"action\":\"add\",\"webviewdomain\":[\"https://test.buyer007.cn\"]}";
        String post2 = PayUtil.httpRequest(url2, "POST", param2);
        System.out.println(post2);

        //todo  设置小程序基本信息

        //todo 设置姓名的时候需要调用api查询是否可用

        return "";

    }

    @RequestMapping("/set")
    public String setAA(){
        ValueOperations ops = cacheRedis.opsForValue();
        ops.set("ABC","123",10);
        return "SUCCES";
    }
    @RequestMapping("/get")
    public String setAAA(){
        ValueOperations ops = cacheRedis.opsForValue();
        Object abc = ops.get("ABC");

        return abc == null?"null":abc.toString();
    }



    /***
     * 封装小程序配置类对象
     * @param info 回调解析的对象
     * @param appletType 小程序的类型
     * @return
     */
    private AppletConfigDO getAppletDO(JSONObject info, String appletType) {
        String accessToken = info.getString("authorizer_access_token");
        String refreshToken = info.getString("authorizer_refresh_token");
        String appid = info.getString("authorizer_appid");
        AppletConfigDO applet = new AppletConfigDO();
        applet.setAppid(appid);
        applet.setAppletType(appletType);
        applet.setStatus(PAY_STATUS_PLATFORM);
        applet.setAuthorizerAccessToken(accessToken);
        applet.setAuthorizerRefreshToken(refreshToken);
        applet.init();
        return applet;
    }

    public static void main(String[] args) {
        String s = "{" +
                "\"authorization_info\": {\n" +
                "\"authorizer_appid\": \"wxf8b4f85f3a794e77\",\n" +
                "\"authorizer_access_token\": \"QXjUqNqfYVH0yBE1iI_7vuN_9gQbpjfK7hYwJ3P7xOa88a89-Aga5x1NMYJyB8G2yKt1KCl0nPC3W9GJzw0Zzq_dBxc8pxIGUNi_bFes0qM\",\n" +
                "\"expires_in\": 7200,\n" +
                "\"authorizer_refresh_token\": \"dTo-YCXPL4llX-u1W1pPpnp8Hgm4wpJtlR6iV0doKdY\",\n" +
                "\"func_info\": [\n" +
                "{\n" +
                "\"funcscope_category\": {\n" +
                "\"id\": 1\n" +
                "}\n" +
                "},\n" +
                "{\n" +
                "\"funcscope_category\": {\n" +
                "\"id\": 2\n" +
                "}\n" +
                "},\n" +
                "{\n" +
                "\"funcscope_category\": {\n" +
                "\"id\": 3\n" +
                "}\n" +
                "}\n" +
                "]\n" +
                "}}";

//        System.out.println(accessToken);
//        System.out.println(refreshToken);
//        System.out.println(appid);

    }

    //    {"authorization_info":{"authorizer_appid":"wx43020a9d04042b56","authorizer_access_token":"11_ENumAbXuzu28zKh-Hf8WJ1dCG3UGJwYhT9fjFASwRpfHDxioituzWxN4TG5NT6tODvXplVTSEqyjGIpQXEfVcF2dUNvL4hdjkTPrh1ALjuC7xIdqMl1zjC0_cOhwn7hvYMeCaIWITysYpHi5HYHiAEDQHQ","expires_in":7200,"authorizer_refresh_token":"refreshtoken@@@pHG_ZxB_wknyZNjSERpeL5Crc5fOlCn098pmRARTiQg","func_info":[{"funcscope_category":{"id":17}},{"funcscope_category":{"id":18},"confirm_info":{"need_confirm":0,"already_confirm":0,"can_confirm":0}},{"funcscope_category":{"id":19}},{"funcscope_category":{"id":25},"confirm_info":{"need_confirm":0,"already_confirm":0,"can_confirm":0}},{"funcscope_category":{"id":30},"confirm_info":{"need_confirm":0,"already_confirm":0,"can_confirm":0}},{"funcscope_category":{"id":31},"confirm_info":{"need_confirm":0,"already_confirm":0,"can_confirm":0}},{"funcscope_category":{"id":36}},{"funcscope_category":{"id":37}},{"funcscope_category":{"id":40}}]}}

    private String getToken() {
        String componentAccessToken = (String) loginCache.get("component_access_token");
        /**判断数据库中是否存在component_access_token*/
        if (!EasyUtil.isStringEmpty(componentAccessToken)) {
            System.out.println("token============" + componentAccessToken);
            /**如果存在，直接返回token的值*/
            return componentAccessToken;
        }
        String componentVerifyTicket = (String) loginCache.get("componentVerifyTicket");
        if (StringUtils.isBlank(componentVerifyTicket)) {
            throw new ErpCommonException("componentVerifyTicket为空");
        }
        String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
        String param = "{\"component_appid\":\"" + componentAppid + "\",\"component_appsecret\": \"" + componentAppsecret + "\",\"component_verify_ticket\":\"" + componentVerifyTicket + "\"}";
        log.info("获取component_access_token:参数===" + param);
        String s = PayUtil.httpRequest(url, "POST", param);
        log.info("获取component_access_token:结果===" + s);
        JSONObject obj = JSON.parseObject(s);
        componentAccessToken = obj.getString("component_access_token");
        log.info("获取component_access_token:componentAccessToken===" + componentAccessToken);
        /**在返回结果中获取token*/
        /**保存token，并设置有效时间*/
        loginCache.putEx("component_access_token", componentAccessToken, 7100L);
        return componentAccessToken;

    }
//    public static void main(String[] args) {
//        ByteArrayInputStream in = new ByteArrayInputStream(result.getBytes());
//        // 读取输入流
//        SAXReader reader = new SAXReader();
//        Document document = reader.read(in);
//        HashMap<String, String> resultMap = new HashMap<>();
//        // 得到xml根元素
//        Element root = document.getRootElement();
//        // 得到根元素的所有子节点
//        List<Element> elementList = root.elements();
//        for (Element element : elementList) {
//            resultMap.put(element.getName(), element.getText());
//        }
//        System.out.println("resultMap");
//        System.out.println(resultMap);
//        String componentVerifyTicket = resultMap.get("ComponentVerifyTicket");
//        System.out.println("componentVerifyTicket");
//    }


}
