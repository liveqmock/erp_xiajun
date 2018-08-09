package com.wangqin.globalshop.usercenter.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.enums.PublishStatus;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.WxPay.PayUtil;
import com.wangqin.globalshop.usercenter.service.IAppletConfigService;
import com.wangqin.globalshop.usercenter.service.UserUploadFileService;
import com.wangqin.globalshop.usercenter.wechat_sdk.AesException;
import com.wangqin.globalshop.usercenter.wechat_sdk.WXBizMsgCrypt;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.Map;

import static com.wangqin.globalshop.usercenter.constant.SysConstants.APPLET_TYPE;
import static com.wangqin.globalshop.usercenter.constant.SysConstants.PAY_STATUS_PROVIDER;

/**
 * @author biscuit
 * @data 2018/07/10
 * 第三方平台授权controller
 */
@RestController
@RequestMapping("/account")
public class Wechat3rdPartyAuthorizationController {
    @Resource
    private Cache loginCache;
    @Autowired
    private UserUploadFileService uploadFileService;
    @Autowired
    private IAppletConfigService appletConfigServiceImplement;
    private static Logger log = LoggerFactory.getLogger("Wechat3rdPartyAuthorization");
    //todo
    private String componentAppid = "wxe25c15397f0ec710";
    private String componentAppsecret = "8eb667a448cb3226d57878acfaca84a0";


    @Value("#{sys.TEMPLET_ID}")
    private Integer templetId;
    @Value("#{sys.WXBASEURL}")
    private String wxBaseUrl;

    /**
     * 回调拿Ticket
     *
     * @param timestamp
     * @param nonce
     * @param msgSignature
     * @param postData
     */

    @RequestMapping("/authorization")
    public void getComponentVerifyTicket(@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("msg_signature") String msgSignature, @RequestBody String postData) {
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

//
//    @RequestMapping("/getHtml")
//    @Authenticated
//    public String getHtml() {
//        String re_url;
//        try {
//            String token = getToken();
//            log.info("获取小程序授权二维码:token===" + token);
//            String url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=" + token;
//            //language=JSON
//            String param = "{\"component_appid\":\"" + componentAppid + "\"}";
//            String post = PayUtil.httpRequest(url, "POST", param);
//            log.info("获取预授权码结果:" + post);
//            JSONObject object = JSON.parseObject(post);
//            /**预授权码*/
//            String preAuthCode = object.getString("pre_auth_code");
//            log.info("预授权码:" + preAuthCode);
//            //todo 配置的是http://"+wxBaseUrl+"/account/queryAuth 微信文档显示 该回调地址必须是http  把 test.buyer007写到配置文件里面去
//            re_url = URLEncoder.encode("http://"+wxBaseUrl+"/account/authcallback", "UTF-8");
//            String reUrl = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + componentAppid + "&pre_auth_code=" + preAuthCode + "&redirect_uri=" + re_url + "&auth_type=2";
//            //todo 有待优化
//            String html = "<html><head><title>Title</title></head><body><a href=\"" + reUrl + "\">授权小程序</a></body></html>";
//            log.info("html:" + html);
//            return html;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        //todo 有待优化
//        return "<html><head><title>Title</title></head><body>获取授权页面失败</body></html>";
//
//
//    }


    @RequestMapping("/getAuthUrl")
//    @Authenticated
    public Object getAuthUrl() {
        JsonResult<Object> result = new JsonResult<>();
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
            //todo 配置的是http://"+wxBaseUrl+"/account/queryAuth 微信文档显示 该回调地址必须是http
            re_url = URLEncoder.encode("http://" + wxBaseUrl + "/#/myApp/appsetTest?companyNo=" + AppUtil.getLoginUserCompanyNo(), "UTF-8");
            String reUrl = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=" + componentAppid + "&pre_auth_code=" + preAuthCode + "&redirect_uri=" + re_url + "&auth_type=2";
            log.info("re_url:" + reUrl);
            return result.buildIsSuccess(true).buildData(reUrl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(true).buildMsg("获取授权页面失败");


    }


    /**
     * 根据auth_code查询授权信息
     * 生成小程序的配置类对象
     *
     * @param authCode  授权成功时获得的授权码
     * @param expiresIn 存活时间
     * @return
     */
    @RequestMapping(value = "/authcallback", method = RequestMethod.GET)
    public Object queryAuth(@RequestParam("companyNo") String companyNo, @RequestParam("auth_code") String authCode, @RequestParam("expires_in") String expiresIn, HttpServletRequest request) {
        JsonResult<Object> result = new JsonResult<>();
        log.info("===================companyNo============================" + companyNo + "----------------------" + request.getRequestURL().toString());
        try {
            log.info("===================进入授权回调============================");
            log.info("auth_code===============" + authCode);
            log.info("expires_in=============" + expiresIn);
            String url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + getToken();
            String param = "{\"component_appid\":\"" + componentAppid + "\",\"authorization_code\":\"" + authCode + "\"}";
            String s = PayUtil.httpRequest(url, "POST", param);
            log.info("===================查询用户授权信息BEGIN============================");
            log.info("请求参数=======" + param);
            log.info("响应=======" + s);
            JSONObject o = JSON.parseObject(s);
            log.info("===================查询用户授权信息END============================");
            JSONObject info = o.getJSONObject("authorization_info");
            /*授权成功之后可以获取到 authorizer_access_token 和 authorizer_refresh_token  如果没有获取 则返回失败*/
            if (!info.containsKey("authorizer_access_token")) {
                return result.buildMsg("授权失败").buildIsSuccess(false);
            }

            AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(companyNo, APPLET_TYPE);
            if (applet != null) {
                String accessToken = info.getString("authorizer_access_token");
                String refreshToken = info.getString("authorizer_refresh_token");
                String appid = info.getString("authorizer_appid");
                if (StringUtils.isBlank(appid) || !appid.equals(applet.getAppid())) {
                    return result.buildMsg("授权失败:当前公司已经绑定了其它小程序,请在微信公众平台解除授权").buildIsSuccess(false);
                }
                applet.setAuthorizerAccessToken(accessToken);
                applet.setAuthorizerRefreshToken(refreshToken);
                applet.setPublishStatus(PublishStatus.AUTHORIZED.getCode());
                applet.update();
                appletConfigServiceImplement.update(applet);
                return result.buildMsg("授权成功").buildIsSuccess(false);
            }
            String token = info.getString("authorizer_access_token");
              /*设置小程序相关的 服务器域名、业务域名*/
            setAppletRequestUrl(token, "set");
            applet = getAppletDO(info, APPLET_TYPE, companyNo);
//            /*提交体验版*/
//            updateApplet(templetId, applet);
//            /*提交审核*/
//            auditApplet(applet);
            log.info("最终小程序信息=======" + applet);
            appletConfigServiceImplement.insert(applet);
            return result.buildMsg("授权成功").buildIsSuccess(false);
        } catch (BizCommonException e) {
            return result.buildMsg("授权失败" + e.getErrorMsg()).buildIsSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            return result.buildMsg("授权失败").buildIsSuccess(false);
        }
    }


//    /**
//     * 设置小程序业务域名和服务器域名
//     *
//     * @return
//     */
////    @PostMapping("/setAppletRequestUrl")
//    public String initApplet() {
//        //设置所有状态为新建的小程序的业务域名和服务器域名
//        // TODO: 2018/7/20
//        setAppletRequestUrl("", "add");
//        return "";
//    }

//    //todo 设置小程序的其他信息
//    @PostMapping("/B")
//    @Authenticated
//    public String setAppletInfo() {
//        //todo 接口未调通  参考相关文档
//        //todo  设置小程序基本信息
//
//        //todo 设置姓名的时候需要调用api查询是否可用
//        //设置小程序名  信息、log
//        return "";
//    }

//    /**
//     * 提交小程序体验版
//     *
//     * @return
//     */
//    @PostMapping("/updateApplet")
//    @Authenticated
//    public Object update() {
//        JsonResult<Object> result = new JsonResult<>();
//        try {
//            AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(AppUtil.getLoginUserCompanyNo(), APPLET_TYPE);
//            updateApplet(templetId, applet);
//            appletConfigServiceImplement.update(applet);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return result.buildMsg("发布失败");
//        }
//        return result.buildIsSuccess(true).buildMsg("发布成功");
//    }

    private void updateApplet(Integer templateId, AppletConfigDO applet) throws IOException {
        String authorizerAccessToken = applet.getAuthorizerAccessToken();
        String url = "https://api.weixin.qq.com/wxa/commit?access_token="+authorizerAccessToken;
        String imgUrl = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=${token}";
        String str = System.currentTimeMillis() + "";
        str = str.substring(3, 13);
        //language=JSON
        String appid = applet.getAppid();
        String param = "{\"template_id\":"+templateId+",\n" +
                "  \"user_version\": \"1.0.1\",\n" +
                "  \"user_desc\": \"新增物流轨迹\",\n" +
                "  \"ext_json\": \"{\\\"extEnable\\\":true,\\\"extAppid\\\":\\\""+ appid +"\\\",\\\"ext\\\":{\\\"userAppId\\\":\\\""+appid+"\\\"}}\"\n" +
                "}";
        //language=JSON

             /*发布所有的满足条件的小程序的体验版  并返回二维码图片  保存到数据库中*/
        log.info("发布结果"+param);
        String post = PayUtil.httpRequest(url, "POST", param);
        log.info("发布结果"+post);

//        String s = HttpClientUtil.get(imgUrl.replace("${token}", applet.getAuthorizerAccessToken()));
//        String img;
//        try (ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(s.getBytes())) {
//            img = uploadFileService.uploadImg(tInputStringStream, applet.getAppid() + str);
//        }
//        applet.setImgUrl(img);
        applet.setTempletId(templateId);
        applet.setPublishStatus(PublishStatus.SUBMITTED.getCode());
//        applet.setExtJson();
        log.info("发布体验版后的小程序" + applet);

    }
    @Getter@Setter
    class Applet{
        private Integer template_id;
        private String user_version;
        private String user_desc;
        private ExtJson ext_json;
    }
    @Getter@Setter
    class ExtJson{
        private Boolean extEnable;
        private String extAppid;
        private Map<String,String> ext;
    }

    @PostMapping("auditAll")
    @ResponseBody
    public String auditAll() {
        log.info("开始提交");
        int i = 0;
        List<AppletConfigDO> list = appletConfigServiceImplement.selectByPublishStatus(PublishStatus.SUBMITTED.getCode());
        for (AppletConfigDO applet : list) {
            i++;
            log.info("提交第一个" + i);
            auditApplet(applet);
            appletConfigServiceImplement.update(applet);
        }
        return "结束";
    }

    @PostMapping("getImgUrl")
    @Authenticated
    @ResponseBody
    public String getImgUrl() {
        AppletConfigDO appletConfigDO = appletConfigServiceImplement.selectByCompanyNoAndType(AppUtil.getLoginUserCompanyNo(), APPLET_TYPE);
        return "https://api.weixin.qq.com/wxa/get_qrcode?access_token=" + appletConfigDO.getAuthorizerAccessToken();
    }

    @PostMapping("publishAll")
    @ResponseBody
    public String publishAll() throws IOException {
        log.info("开始提交");
        int i = 0;
        List<AppletConfigDO> list = appletConfigServiceImplement.selectByPublishStatus(PublishStatus.AUTHORIZED.getCode());
        for (AppletConfigDO applet : list) {
            i++;
            log.info("提交第一个" + i);
            updateApplet(templetId, applet);

            appletConfigServiceImplement.update(applet);
        }
        return "结束";
    }

    @PostMapping("/getAuditStatusWithAuditId")
    @ResponseBody
    public String getAuditStatusWithAuditId(String companyNo) {
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(companyNo, APPLET_TYPE);
        String accessToken = applet.getAuthorizerAccessToken();
        String url = "https://api.weixin.qq.com/wxa/get_auditstatus?access_token=" + accessToken;
        //language=JSON
        String param = "{\"auditid\":\"" + applet.getAuditId() + "\"}";
        String post = PayUtil.httpRequest(url, "POST", param);
        return post;

    }

    @PostMapping("/getAuditStatus")
    @ResponseBody
    public String getAuditStatus(String companyNo) {
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(companyNo, APPLET_TYPE);
        String accessToken = applet.getAuthorizerAccessToken();
        String url = "https://api.weixin.qq.com/wxa/get_latest_auditstatus?access_token=" + accessToken;
        String s = HttpClientUtil.get(url);
        return s;

    }

    @PostMapping("/release")
    @ResponseBody
    public String release(String companyNo) {
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(companyNo, APPLET_TYPE);
        String accessToken = applet.getAuthorizerAccessToken();
        String url1 = "https://api.weixin.qq.com/wxa/release?access_token=" + accessToken;
        String param1 = "{}";
        String post1 = PayUtil.httpRequest(url1, "POST", param1);
        log.info("发布成功" + post1);
        return post1;
    }
//    @PostMapping("/updateApletAll")
////    @Authenticated
//    public Object updateAplet(Integer templateId) {
//        JsonResult<Object> result = new JsonResult<>();
//        try {
//            //todo list
//            List<AppletConfigDO> list = new ArrayList<>();
//            for (AppletConfigDO applet : list) {
//                updateApplet(templateId, applet);
//                appletConfigServiceImplement.update(applet);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void auditApplet(AppletConfigDO applet) throws BizCommonException {
        String authorizerAccessToken = applet.getAuthorizerAccessToken();
        String url1 = "https://api.weixin.qq.com/wxa/get_category?access_token=" + authorizerAccessToken;
        String s1 = HttpClientUtil.get(url1);
        log.info("获取分类结果" + s1);
        JSONObject o1 = JSON.parseObject(s1);
        if (!"ok".equals(o1.getString("errmsg"))) {
            throw new BizCommonException("获取用户小程序分类失败");
        }
        String category_list = o1.getString("category_list");
        JSONArray objects = JSON.parseArray(category_list);
        Object o = objects.get(0);
        JSONObject object1 = JSON.parseObject(o.toString());
//        {"errcode":0,"errmsg":"ok","category_list":[{"first_class":"工具","second_class":"办公","first_id":287,"second_id":298}]}
        String firstClass = object1.getString("first_class");
        String secondClass = object1.getString("second_class");
        String firstId = object1.getString("first_id");
        String secondId = object1.getString("second_id");
        log.info("firstClass" + firstClass);
        log.info("secondClass" + secondClass);
        log.info("firstId" + firstId);
        log.info("secondId" + secondId);

//        {"errcode":0,"errmsg":"ok","category_list":[{"first_class":"商家自营","second_class":"海淘","first_id":304,"second_id":784}]}
        String url2 = "https://api.weixin.qq.com/wxa/get_page?access_token=" + authorizerAccessToken;
        String s2 = HttpClientUtil.get(url2);
        log.info("获取页面结果" + s2);
        JSONObject o2 = JSON.parseObject(s2);
        if (!"ok".equals(o2.getString("errmsg"))) {

        }
        JSONArray pageList = o2.getJSONArray("page_list");
        log.info("pageList" + pageList);
        if (pageList.isEmpty()) {
            throw new BizCommonException("小程序主页为空");
        }
        String index = pageList.getString(0);
        log.info("审核页面信息" + index);
        //{"errcode":0,"errmsg":"ok","page_list":["pages\/index\/index","pages\/index\/webView","pages\/index\/special","pages\/order\/detail","pages\/order\/list","pages\/item\/list","pages\/user\/main","pages\/user\/about","pages\/user\/service","pages\/user\/address","pages\/user\/addAddress","pages\/user\/editAddress","pages\/cart\/list","pages\/cart\/lists","pages\/order\/allExpress","pages\/order\/preview","pages\/item\/detail"]}

        String url3 = "https://api.weixin.qq.com/wxa/submit_audit?access_token=" + authorizerAccessToken;
        //language=JSON
        String param3 = "{\"item_list\":[{\"address\":\"" + index + "\",\"tag\": \"购物 海淘 时尚\",\"first_class\": \"" + firstClass + "\",\"second_class\": \"" + secondClass + "\",\"first_id\": " + firstId + ",\"second_id\":" + secondId + ",\"title\":\"首页\"}]}";
        String post3 = PayUtil.httpRequest(url3, "POST", param3);
//        {"errcode":0,"errmsg":"ok","auditid":430371682}
        JSONObject obj3 = JSON.parseObject(post3);
        log.info("提交审核的回调" + obj3.toJSONString());
        String auditid = obj3.getString("auditid");
        log.info("auditid" + auditid);
        applet.setAuditId(auditid);
        applet.setPublishStatus(PublishStatus.PENDING_REVIEW.getCode());
        log.info("提交审核之后的" + applet);
    }

    /***
     * 设置小程序的业务域名
     * @param token
     * @param type
     */
    private void setAppletRequestUrl(String token, String type) {
        /*设置小程序服务器域名*/
        String url = "https://api.weixin.qq.com/wxa/modify_domain?access_token=" + token;
        //todo
        //language=JSON
        String param = "{\"action\":\"" + type + "\",\"requestdomain\":[\"https://"+wxBaseUrl+"\"],\"wsrequestdomain\":[\"wss://"+wxBaseUrl+"\"],\"uploaddomain\":[\"https://"+wxBaseUrl+"\"], \"downloaddomain\":[\"https://"+wxBaseUrl+"\"]}";
        String post1 = PayUtil.httpRequest(url, "POST", param);
        log.info("设置小程序服务器域名结果" + post1);
        /*设置小程序业务域名*/
        String url1 = "https://api.weixin.qq.com/wxa/setwebviewdomain?access_token=" + token;
        //language=JSON
        String param1 = "{\"action\":\"" + type + "\",\"webviewdomain\":[\"https://"+wxBaseUrl+"\"]}";
        String post = PayUtil.httpRequest(url1, "POST", param1);
        log.info("设置小程序业务域名结果" + post);
    }

    /***
     * 每三十分钟刷新第三方平台的token和各个授权商户的token
     */
    @Scheduled(cron = "0 0/30 * * * ?")
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
            String param = "{\n" + "\"component_appid\":\"" + componentAppid + "\"," + "\"authorizer_appid\":\"" + appid + "\"," + "\"authorizer_refresh_token\":\"" + refreshToken + "\"" + "}";
            log.info("============appid->" + appid + "=================");
            log.info("param=================" + param);
            String post = PayUtil.httpRequest(url, "POST", param);
            log.info("回调结果===============" + post);
            log.info("============appid<-" + appid + "=================");
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
    }

    /***
     * 获取各个审核中通过的
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    private void publicapplet() {
        log.info("发布小程序定时任务启动");
        List<AppletConfigDO> list = appletConfigServiceImplement.selectByPublishStatus(PublishStatus.PENDING_REVIEW.getCode());
        for (AppletConfigDO applet : list) {
            String accessToken = applet.getAuthorizerAccessToken();
            String url = "https://api.weixin.qq.com/wxa/get_auditstatus?access_token=" + accessToken;
            //language=JSON
            String param = "{\"auditid\":\"" + applet.getAuditId() + "\"}";
            String post = PayUtil.httpRequest(url, "POST", param);
            log.info("回调" + post);
//            {"errcode":0,"errmsg":"ok","status":1,"reason":"1:小程序内容不符合规则:<br>(1):页面无正式运营内容，请上架完整内容再提交审核。（测试环境wifi\/4G，ios11.3.1，微信6.6.7，iPhone6）<br>"}
            JSONObject obj = JSON.parseObject(post);
            String status = obj.getString("status");
            log.info("查询状态" + status);
            if ("0".equals(status)) {
                String url1 = "https://api.weixin.qq.com/wxa/release?access_token=" + accessToken;
                String param1 = "{}";
                String post1 = PayUtil.httpRequest(url1, "POST", param1);
                log.info("发布成功" + post1);
                applet.setPublishStatus(PublishStatus.PUBLISHED.getCode());
                appletConfigServiceImplement.update(applet);
            }
        }
    }

    @RequestMapping("getInfo")
    public String getInfo() {
        String componentAccessToken = getToken();
        log.info("小程序获取token====" + componentAccessToken);
        return componentAccessToken;
    }

    @RequestMapping("get")
    public String get() {
        return wxBaseUrl;

    }

    @RequestMapping("binding")
    public String binding(String companyNo) {
        AppletConfigDO applet = appletConfigServiceImplement.selectByCompanyNoAndType(companyNo, APPLET_TYPE);
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + applet.getAppid() + "&secret=" + applet.getSecret();
        String s = HttpClientUtil.get(url);
        JSONObject obj = JSON.parseObject(s);
        String token = obj.getString("access_token");
        String s1 = "https://api.weixin.qq.com/cgi-bin/open/bind?access_token=" + token;
        //language=JSON
        String param = "{\"appid\":" + applet.getAppid() + ",\"open_appid\":" + componentAppid + "}";
        String post = PayUtil.httpRequest(s1, "POST", param);

        return post;

    }


    /***
     * 封装小程序配置类对象
     * @param info 回调解析的对象
     * @param appletType 小程序的类型
     * @param companyNo
     * @return
     */
    private AppletConfigDO getAppletDO(JSONObject info, String appletType, String companyNo) {
        String accessToken = info.getString("authorizer_access_token");
        String refreshToken = info.getString("authorizer_refresh_token");
        String appid = info.getString("authorizer_appid");
        AppletConfigDO applet = new AppletConfigDO();
        applet.setAppid(appid);
        applet.setAppletType(appletType);
        applet.setCompanyNo(companyNo);
        applet.setStatus(PAY_STATUS_PROVIDER);
        applet.setPublishStatus(PublishStatus.AUTHORIZED.getCode());
        applet.setAuthorizerAccessToken(accessToken);
        applet.setAuthorizerRefreshToken(refreshToken);
        applet.init4NoLogin();
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
            throw new BizCommonException("componentVerifyTicket为空");
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

    private void initToken() {
        String componentVerifyTicket = (String) loginCache.get("componentVerifyTicket");
        if (StringUtils.isBlank(componentVerifyTicket)) {
            throw new BizCommonException("componentVerifyTicket为空");
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
