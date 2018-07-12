package com.wangqin.globalshop.usercenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
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
import org.springframework.scheduling.annotation.Scheduled;
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
    private IAppletConfigService appletConfigServiceImplement;
    private static Logger log = LoggerFactory.getLogger("Wechat3rdPartyAuthorization");
    //todo
    private String componentAppid = "wxe25c15397f0ec710";
    private String componentAppsecret = "8eb667a448cb3226d57878acfaca84a0";

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



    @RequestMapping("/getHtml")
    @Authenticated
    public String getHtml() {
        String re_url;
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
            //todo 配置的是http://test.buyer007.cn/account/queryAuth 微信文档显示 该回调地址必须是http  把 test.buyer007写到配置文件里面去
            re_url = URLEncoder.encode("http://tests.buyer007.cn/account/authcallback", "UTF-8");
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
     *
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
        AppletConfigDO applet = getAppletDO(info, APPLET_TYPE);

        appletConfigServiceImplement.insert(applet);
        System.out.println("----------------授权回调接口-------------");
        return "success";
    }

    //todo 设置小程序初始信息的
    @PostMapping("/A")
    @Authenticated
    public String initApplet() {
        //    设置小程序服务器域名
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(AppUtil.getLoginUserCompanyNo(), APPLET_TYPE);
        String accessToken = applet.getAuthorizerAccessToken();
        String url1 = "https://api.weixin.qq.com/wxa/modify_domain?access_token=" + accessToken;
        //todo 设置
        String param1 = "{\"action\":\"add\",\"requestdomain\":[\"https://test.buyer007.cn\"],\"wsrequestdomain\":[\"wss://test.buyer007.cn\"],\"uploaddomain\":[\"https://test.buyer007.cn\"], \"downloaddomain\":[\"https://test.buyer007.cn\"]}";
        String post1 = PayUtil.httpRequest(url1, "POST", param1);
        System.out.println(post1);

//        设置小程序业务域名
        String url2 = "https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=" + accessToken;
        //language=JSON
        String param2 = "{\"action\":\"add\",\"webviewdomain\":[\"https://test.buyer007.cn\"]}";
        String post2 = PayUtil.httpRequest(url2, "POST", param2);
        System.out.println(post2);



        return "";

    }
    //todo 设置小程序的其他信息
    @PostMapping("/B")
    @Authenticated
    public String setAppletInfo() {
        //todo 接口未调通  参考相关文档
        //todo  设置小程序基本信息

        //todo 设置姓名的时候需要调用api查询是否可用
        //设置小程序名  信息、log
        return "";
    }

    //todo 上传代码返回二维码地址
    @PostMapping("/C")
    @Authenticated
    public String updateAplet() {
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(AppUtil.getLoginUserCompanyNo(), APPLET_TYPE);
        String authorizerAccessToken = applet.getAuthorizerAccessToken();
        String url = "https://api.weixin.qq.com/wxa/commit?access_token=" + authorizerAccessToken;

        String param = "{\"template_id\": 2,\n" +
                "  \"user_version\": \"2018-01-17 09:39:57\",\n" +
                "  \"user_desc\": \"修复bug\",\n" +
                "  \"ext_json\": \"{\\\"extEnable\\\":true,\\\"extAppid\\\":\\\"wx43020a9d04042b56\\\",\\\"ext\\\":{\\\"userAppId\\\":\\\"wx43020a9d04042b56\\\"}}\"\n" +
                "}";
        //todo param需要存到数据库中
        System.out.println(param);
        String post = PayUtil.httpRequest(url, "POST", param);
        //todo 对结果进行业务逻辑处理  成功则返回体验版二维码


        String url1 = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=" + authorizerAccessToken;
        String s = HttpClientUtil.get(url1);
        /*二维码图片的地址*/
        return s;
    }

    @PostMapping("/D")
    @Authenticated
    public String auditApplet() {
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(AppUtil.getLoginUserCompanyNo(), APPLET_TYPE);
        String authorizerAccessToken = applet.getAuthorizerAccessToken();
        String url1 = "https://api.weixin.qq.com/wxa/get_category?access_token=" + authorizerAccessToken;
        String s1 = HttpClientUtil.get(url1);
        System.out.println(s1);
//        {"errcode":0,"errmsg":"ok","category_list":[{"first_class":"商家自营","second_class":"海淘","first_id":304,"second_id":784}]}

        String url2 = "https://api.weixin.qq.com/wxa/get_page?access_token=" + authorizerAccessToken;
        String s2 = HttpClientUtil.get(url2);
        System.out.println(s2);
 //{"errcode":0,"errmsg":"ok","page_list":["pages\/index\/index","pages\/index\/webView","pages\/index\/special","pages\/order\/detail","pages\/order\/list","pages\/item\/list","pages\/user\/main","pages\/user\/about","pages\/user\/service","pages\/user\/address","pages\/user\/addAddress","pages\/user\/editAddress","pages\/cart\/list","pages\/cart\/lists","pages\/order\/allExpress","pages\/order\/preview","pages\/item\/detail"]}

        //todo 根据前两个api的返回结果  和 前段参数 拼装成 param参数
        String url3 = "https://api.weixin.qq.com/wxa/submit_audit?access_token=" + authorizerAccessToken;
        //language=JSON
        String param3 = "{" +
                "    \"item_list\": [" +
                "    {" +
                "        \"address\":\"pages\\/index\\/index\"," +
                "        \"tag\":\"购物 海淘 时尚\"," +
                "        \"first_class\": \"商家自营\"," +
                "        \"second_class\": \"海淘\"," +
                "        \"first_id\":304," +
                "        \"second_id\":784," +
                "        \"title\": \"首页\"" +
                "    }" +
                "]" +
                "}";
        String post3 = PayUtil.httpRequest(url3, "POST", param3);
//        {"errcode":0,"errmsg":"ok","auditid":430371682}
        System.out.println(post3);
        //todo 处理成功返回的业务  并保存到相关数据库  其中的auditid就是当前提交的版本号
        return "";
    }

    /***
     * 每三十分钟刷新第三方平台的token和各个授权商户的token
     */
    @Scheduled(cron = "* */5 * * * ?")
    private void freashToken() {
        log.info("开始刷新各个授权商户的token");
        initToken();
        List<AppletConfigDO> list = appletConfigServiceImplement.list();
        String token = getToken();
        for (AppletConfigDO applet : list) {
            String appid = applet.getAppid();
            String refreshToken = applet.getAuthorizerRefreshToken();
            if (StringUtils.isBlank(refreshToken)) {
                continue;
            }
            String url = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token=" + token;
            //language=JSON
            String param = "{\n" +
                    "\"component_appid\":\"" + componentAppid + "\"," +
                    "\"authorizer_appid\":\"" + appid + "\"," +
                    "\"authorizer_refresh_token\":\"" + refreshToken + "\"" +
                    "}";
            log.info("============appid->"+appid+"=================");
            log.info("param================="+param);
            String post = PayUtil.httpRequest(url, "POST", param);
            log.info("回调结果==============="+post);
            log.info("============appid<-"+appid+"=================");
            JSONObject object = JSON.parseObject(post);
            applet.setAuthorizerRefreshToken(object.getString("authorizer_refresh_token"));
            applet.setAuthorizerAccessToken(object.getString("authorizer_access_token"));
            try {
                log.info("开始更新");
                appletConfigServiceImplement.update(applet);
                log.info("结束更新");
            } catch (Exception e) {
                log.error("appid为" + appid + "刷新token失败");
            }

        }

//    11_E-AmSYNCYcn-HjhDMboOnVZ3afXdNKZ_0GPmrZYE7od728dFJJu1DKVF2u4IB83310RCNZ1aFyK4ecduBqlY5jLUOql3LZbET5NJP612kiLblFa5qedDPUhNjfZdhCCett8J8iFlXP7GFB6oVEWaAFDLUM
    }
    /***
     * 获取各个审核中通过的
     */
    @Scheduled(cron = "* */59 * * * ?")
    private void publicapplet() {
        log.info("发布小程序定时任务启动");
        //todo 遍历相关数据库  拿到所有审核中的小程序的 auditid 和相应的 authorizer_access_token

        String accessToken = "";
        String url = "https://api.weixin.qq.com/wxa/get_auditstatus?access_token="+accessToken;
        //todo
        //language=JSON
//        String param ="{\"auditid\":\"430371682\"}";
//        String post = PayUtil.httpRequest(url, "POST", param);
//        审核状态，其中0为审核成功，1为审核失败，2为审核中
//        System.out.println(post);
//
//        //todo 处理返回参数  如果为0
//
//        String url1 = "https://api.weixin.qq.com/wxa/release?access_token="+accessToken;
//        //language=JSON
//        String param1 = "{}";
//        String post1 = PayUtil.httpRequest(url1, "POST", param1);
//        System.out.println(post1);


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

    public void initToken() {
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
        String componentAccessToken = obj.getString("component_access_token");
        log.info("获取component_access_token:componentAccessToken===" + componentAccessToken);
        /**在返回结果中获取token*/
        /**保存token，并设置有效时间*/
        loginCache.putEx("component_access_token", componentAccessToken, 7100L);

    }


}
