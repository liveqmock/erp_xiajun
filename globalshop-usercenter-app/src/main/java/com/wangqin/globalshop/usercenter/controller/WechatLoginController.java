package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CookieUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.usercenter.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/20
 */
@Controller
@RequestMapping("/wechatLogin")
@ResponseBody
public class WechatLoginController {
    @Value("#{sys.SESSION_ID}")
    private String SESSION_ID;
    @Value("#{sys.COMPANY_NO}")
    private String COMPANY_NO;
    @Value("#{sys.APPSECRET}")
    private String APPSECRET;
    @Value("#{sys.appid}")
    private String appid;
    @Value("#{sys.sysurl}")
    private String sysurl;
    @Value("#{sys.TIMEOUT}")
    private static long TIMEOUT;

    @Autowired
    private IUserService userService;
    @Autowired
    private Cache loginCache;


    @RequestMapping("/login")
    public Object login(HttpServletRequest request, HttpServletResponse response, String code) {
        JsonResult<List<AuthUserDO>> result = new JsonResult<>();
        try {
            /**获取openId和token*/
            JSONObject o = HttpClientUtil.post("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code", null,
                    null, "2");
            /**如果相应包含errcode  表示请求失败*/
            if (o.containsKey("errcode")) {
                return result.buildIsSuccess(false).buildMsg(o.getString("errmsg"));
            }
            String unionid = o.getString("unionid");
            List<AuthUserDO> list = userService.selectByUnionid(unionid);
            if (list.size() > 1) {
                return result.buildIsSuccess(true).buildData(list);
            }
            if (list.size() == 1) {
                AuthUserDO user = list.get(0);
                String sessionId = (String) request.getAttribute(SESSION_ID);
                if (StringUtils.isBlank(sessionId)) {
                    sessionId = CookieUtil.getCookieValue(request, SESSION_ID);
                }

                loginCache.putEx(sessionId, user.getName(), TIMEOUT);
                loginCache.putEx(COMPANY_NO + sessionId, user.getCompanyNo(), TIMEOUT);
                AppUtil.setLoginUser(user.getName(), user.getCompanyNo());
                response.sendRedirect(sysurl);
                return result.buildIsSuccess(true).buildMsg("登陆成功");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(false).buildMsg("您还不是本平台的用户,请联系公司管理员进行授权后登陆");

    }

    @RequestMapping("/authorized")
    public Object authorized(String code, String state) {
        JsonResult<List<AuthUserDO>> result = new JsonResult<>();

        /**获取openId和token*/
        JSONObject o = HttpClientUtil.post("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + APPSECRET + "&code=" + code + "&grant_type=authorization_code", null,
                null, "2");
        /**如果相应包含errcode  表示请求失败*/
        if (o.containsKey("errcode")) {
            return result.buildIsSuccess(false).buildMsg(o.getString("errmsg"));
        }
        String unionid = o.getString("unionid");
        List<AuthUserDO> list = userService.selectByUnionid(unionid);
        if (hasAuthUser(list, state)) {
            return result.buildIsSuccess(false).buildMsg("您已存在当前公司的账户，不允许重复绑定");
        }

        String openid = o.getString("openid");
        String accessToken = o.getString("access_token");
        /**根据token获取用户的信息*/
        JSONObject object = HttpClientUtil.post("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openid, null,
                null, "2");
        WxUserDO user = new WxUserDO();
        user.setCompanyNo(state);
        user.setGender(Integer.valueOf(object.getString("sex")));
        user.setNickName(object.getString("nickname"));
        user.setProvince(object.getString("province"));
        user.setCity(object.getString("city"));
        user.setCountry(object.getString("country"));
        user.setOpenId(openid);
        user.setUnionId(unionid);
        user.setAvatarUrl(object.getString("headimgurl"));
        userService.addUserByqrcode(state, user);
        return result.buildIsSuccess(true).buildMsg("授权成功");
    }

    private boolean hasAuthUser(List<AuthUserDO> list, String state) {
        for (AuthUserDO authUserDO : list) {
            if (state.equals(authUserDO.getCompanyNo())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取微信登陆二维码的链接
     *
     * @return
     */
    @RequestMapping("/getUrl")
    public Object getUrl() {
        JsonResult<Object> result = new JsonResult<>();
        try {
            String baseUrl = sysurl + "/wechatLogin/login";
            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfcdeefc831b3e8c4&redirect_uri=" + baseUrl + "&response_type=code&scope=snsapi_login";
//            if ("authorized".equals(type)) {
//                String state = AppUtil.getLoginUserCompanyNo();
//                url = url + "&state=" + state;
//            }
            return result.buildData(url).buildIsSuccess(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(false).buildMsg("获取失败");

    }

//    @RequestMapping("/getImgUrl")
//    public Object getImgUrl() {
//        JsonResult<Object> result = new JsonResult<>();
//        try {
//            String baseUrl = sysurl + "/wechatLogin/authorized";
//            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");
//            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfcdeefc831b3e8c4&redirect_uri=" + baseUrl + "&response_type=code&scope=snsapi_login";
////            String state = AppUtil.getLoginUserCompanyNo();
//            String state = "123";
//            url = url + "&state=" + state;
//            String s = HttpClientUtil.get(url);
//
//
//            return result.buildData(url).buildIsSuccess(true);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return result.buildIsSuccess(false).buildMsg("获取失败");
//
//    }
    @RequestMapping("/getImgUrl")
    public Object getImgUrl() {
        JsonResult<Object> result = new JsonResult<>();
        return result.buildData("https://open.weixin.qq.com/connect/qrcode/081fNLs3OTjRKaOo").buildIsSuccess(false).buildMsg("获取失败");

    }

    public static void main(String[] args) {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>微信登录</title>\n" +
                "\t\t<meta charset=\"utf-8\">\t\t\n" +
                "\t\t<link rel=\"stylesheet\" href=\"https://res.wx.qq.com/connect/zh_CN/htmledition/style/impowerApp3696b4.css\">\n" +
                "\t\t<link href=\"https://res.wx.qq.com/connect/zh_CN/htmledition/images/favicon3696b4.ico\" rel=\"Shortcut Icon\">\n" +
                "\t\t<script src=\"https://res.wx.qq.com/connect/zh_CN/htmledition/js/jquery.min3696b4.js\"></script>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<div class=\"main impowerBox\">\n" +
                "\t\t\t<div class=\"loginPanel normalPanel\">\n" +
                "\t\t\t\t<div class=\"title\">微信登录</div>\n" +
                "\t\t\t\t<div class=\"waiting panelContent\">\n" +
                "\t\t\t\t\t<div class=\"wrp_code\"><img class=\"qrcode lightBorder\" src=\"/connect/qrcode/081fNLs3OTjRKaOo\" /></div>\n" +
                "\t\t\t\t\t<div class=\"info\">\n" +
                "\t\t\t\t\t\t<div class=\"status status_browser js_status\" id=\"wx_default_tip\">\n" +
                "\t\t\t                <p>请使用微信扫描二维码登录</p>\n" +
                "                            <p>“网擒天下”</p>\n" +
                "\t\t\t            </div>\n" +
                "\t\t\t            <div class=\"status status_succ js_status\" style=\"display:none\" id=\"wx_after_scan\">\n" +
                "\t\t\t                <i class=\"status_icon icon38_msg succ\"></i>\n" +
                "\t\t\t                <div class=\"status_txt\">\n" +
                "\t\t\t                    <h4>扫描成功</h4>\n" +
                "\t\t\t                    <p>请在微信中点击确认即可登录</p>\n" +
                "\t\t\t                </div>\n" +
                "\t\t\t            </div>\n" +
                "\t\t\t            <div class=\"status status_fail js_status\" style=\"display:none\" id=\"wx_after_cancel\">\n" +
                "\t\t\t                <i class=\"status_icon icon38_msg warn\"></i>\n" +
                "\t\t\t                <div class=\"status_txt\">\n" +
                "\t\t\t                    <h4>您已取消此次登录</h4>\n" +
                "\t\t\t                    <p>您可再次扫描登录，或关闭窗口</p>\n" +
                "\t\t\t                </div>\n" +
                "\t\t\t            </div>\n" +
                "\t\t\t        </div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "        <script>\n" +
                "function AQ_SECAPI_ESCAPE(a,b){for(var c=new Array,d=0;d<a.length;d++)if(\"&\"==a.charAt(d)){var e=[3,4,5,9],f=0;for(var g in e){var h=e[g];if(d+h<=a.length){var i=a.substr(d,h).toLowerCase();if(b[i]){c.push(b[i]),d=d+h-1,f=1;break}}}0==f&&c.push(a.charAt(d))}else c.push(a.charAt(d));return c.join(\"\")}function AQ_SECAPI_CheckXss(){for(var a=new Object,b=\"'\\\"<>`script:daex/hml;bs64,\",c=0;c<b.length;c++){for(var d=b.charAt(c),e=d.charCodeAt(),f=e,g=e.toString(16),h=0;h<7-e.toString().length;h++)f=\"0\"+f;a[\"&#\"+e+\";\"]=d,a[\"&#\"+f]=d,a[\"&#x\"+g]=d}a[\"&lt\"]=\"<\",a[\"&gt\"]=\">\",a[\"&quot\"]='\"';var i=location.href,j=document.referrer;i=decodeURIComponent(AQ_SECAPI_ESCAPE(i,a)),j=decodeURIComponent(AQ_SECAPI_ESCAPE(j,a));var k=new RegExp(\"['\\\"<>`]|script:|data:text/html;base64,\");if(k.test(i)||k.test(j)){var l=\"1.3\",m=\"http://zyjc.sec.qq.com/dom\",n=new Image;n.src=m+\"?v=\"+l+\"&u=\"+encodeURIComponent(i)+\"&r=\"+encodeURIComponent(j),i=i.replace(/['\\\"<>`]|script:/gi,\"\"),i=i.replace(/data:text\\/html;base64,/gi,\"data:text/plain;base64,\"),location.href=i}}AQ_SECAPI_CheckXss();\n" +
                "</script>\n" +
                "        <script>\n" +
                "!function(){function a(d){jQuery.ajax({type:\"GET\",url:\"https://long.open.weixin.qq.com/connect/l/qrconnect?uuid=081fNLs3OTjRKaOo\"+(d?\"&last=\"+d:\"\"),dataType:\"script\",cache:!1,timeout:6e4,success:function(d,e,f){var g=window.wx_errcode;switch(g){case 405:var h=\"http://test.buyer007.cn/wechatLogin/authorized\";h=h.replace(/&amp;/g,\"&\"),h+=(h.indexOf(\"?\")>-1?\"&\":\"?\")+\"code=\"+wx_code+\"&state=123\";var i=b(\"self_redirect\");if(c)if(\"true\"!==i&&\"false\"!==i)try{document.domain=\"qq.com\";var j=window.top.location.host.toLowerCase();j&&(window.location=h)}catch(k){window.top.location=h}else if(\"true\"===i)try{window.location=h}catch(k){window.top.location=h}else window.top.location=h;else window.location=h;break;case 404:jQuery(\".js_status\").hide(),jQuery(\"#wx_after_scan\").show(),setTimeout(a,100,g);break;case 403:jQuery(\".js_status\").hide(),jQuery(\"#wx_after_cancel\").show(),setTimeout(a,2e3,g);break;case 402:case 500:window.location.reload();break;case 408:setTimeout(a,2e3)}},error:function(b,c,d){var e=window.wx_errcode;408==e?setTimeout(a,5e3):setTimeout(a,5e3,e)}})}function b(a,b){b||(b=window.location.href),a=a.replace(/[\\[\\]]/g,\"\\\\$&\");var c=new RegExp(\"[?&]\"+a+\"(=([^&#]*)|&|#|$)\"),d=c.exec(b);return d?d[2]?decodeURIComponent(d[2].replace(/\\+/g,\" \")):\"\":null}var c=window.top!=window;if(c){var d=\"\";\"white\"!=d&&(document.body.style.color=\"#373737\")}else{document.getElementsByClassName||(document.getElementsByClassName=function(a){for(var b=[],c=new RegExp(\"(^| )\"+a+\"( |$)\"),d=document.getElementsByTagName(\"*\"),e=0,f=d.length;f>e;e++)c.test(d[e].className)&&b.push(d[e]);return b}),document.body.style.backgroundColor=\"#333333\",document.body.style.padding=\"50px\";for(var e=document.getElementsByClassName(\"status\"),f=0,g=e.length;g>f;++f){var h=e[f];h.className=h.className+\" normal\"}}var i=\"\";if(i){var j=document.createElement(\"link\");j.rel=\"stylesheet\",j.href=i.replace(new RegExp(\"javascript:\",\"gi\"),\"\"),document.getElementsByTagName(\"head\")[0].appendChild(j)}setTimeout(a,100)}();\n" +
                "</script>\n" +
                "\t</body>\n" +
                "</html>\n";
    }


}
