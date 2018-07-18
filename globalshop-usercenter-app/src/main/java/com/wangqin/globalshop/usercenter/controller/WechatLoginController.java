package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CookieUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.usercenter.service.IUserService;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static long TIMEOUT = 30 * 60 * 1000;

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
            return result.buildIsSuccess(false).buildMsg("跳转地址错误：" + sysurl);
//            e.printStackTrace();
        }
        return result.buildIsSuccess(false).buildMsg("您还不是本平台的用户,请联系公司管理员进行授权后登陆");

    }

    @RequestMapping("/authorized")
    public Object authorized(String code, String state, HttpServletResponse response) {
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
            List<AuthUserDO> list = userService.selectByUnionidAndCompanyNo(unionid, state);
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
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            ServletOutputStream out = response.getOutputStream();
            out.print("chang");
//            out.print(JSON.toJSONString(result.buildIsSuccess(true).buildMsg("授权成功")));
//            response.sendRedirect(sysurl+"/#/permission/user");
        } catch (ErpCommonException e) {
            return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            return result.buildData(url).buildIsSuccess(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(false).buildMsg("获取失败");

    }

    public static void main(String[] args) throws IOException {
        String baseUrl = "https://test.buyer007.cn" + "/wechatLogin/authorized";
        baseUrl = URLEncoder.encode(baseUrl, "UTF-8");
        String url = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfcdeefc831b3e8c4&redirect_uri=" + baseUrl + "&response_type=code&scope=snsapi_login";
        url = url + "&state=" + "ZeYbipA0xN";
        System.out.println(url);


    }

    /**
     * 获取微信授权二维码的图片链接
     *
     * @return
     */
    @RequestMapping("/getImgUrl")
    public Object getImgUrl() {
        JsonResult<Object> result = new JsonResult<>();
        try {
            String baseUrl = sysurl + "/wechatLogin/authorized";
            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfcdeefc831b3e8c4&redirect_uri=" + baseUrl + "&response_type=code&scope=snsapi_login";
            String state = AppUtil.getLoginUserCompanyNo();
            url = url + "&state=" + state;
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("img");
            if (elements.size() != 1) {
                return result.buildIsSuccess(false).buildMsg("二维码链接有误");
            }
            String img = "";
            for (Element element : elements) {
                img = element.attr("src");
            }

            return result.buildData("https://open.weixin.qq.com" + img).buildIsSuccess(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(false).buildMsg("获取失败");

    }

    /**
     * 获取微信授权二维码的链接
     *
     * @return
     */
    @RequestMapping("/getAuthorizedUrl")
    public Object getAuthorizedUrl() {
        JsonResult<Object> result = new JsonResult<>();
        try {
            String baseUrl = sysurl + "/wechatLogin/authorized";
            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfcdeefc831b3e8c4&redirect_uri=" + baseUrl + "&response_type=code&scope=snsapi_login";
            String state = AppUtil.getLoginUserCompanyNo();
            url = url + "&state=" + state;
            return result.buildData(url).buildIsSuccess(true).buildMsg("获取成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return result.buildIsSuccess(false).buildMsg("获取失败");
        }

    }

    /**
     * 获取微信授权二维码的网页
     *
     * @return
     */
    @RequestMapping("/getHtml")
    public void getImgHtml(HttpServletResponse response) {
        String baseUrl = sysurl + "/wechatLogin/authorized";
        try {
            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");

            String str = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "  <title></title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script src=\"https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js\"></script>\n" +
                    "    <div id=\"login_container\"></div>\n" +
                    "    <script>\n" +
                    "      var obj = new WxLogin\n" +
                    "      ({\n" +
                    "          id:\"login_container\",//div的id\n" +
                    "          appid: \"" + appid + "\",\n" +
                    "          scope: \"snsapi_login\",//写死\n" +
                    "          redirect_uri: '" + baseUrl + "',\n" +
                    "          state: \"" + AppUtil.getLoginUserCompanyNo() + "\",\n" +
                    "          style: \"black\",\n" +
                    "      });\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>\n";
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(str);
            writer.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取微信授权二维码的网页
     *
     * @return
     */
    @RequestMapping("/getLoginHtml")
    public void getLoginHtml(HttpServletResponse response) {
        String baseUrl = sysurl + "/wechatLogin/login";
        try {
            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");

            String str = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "  <title></title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <script src=\"https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js\"></script>\n" +
                    "    <div id=\"login_container\"></div>\n" +
                    "    <script>\n" +
                    "      var obj = new WxLogin\n" +
                    "      ({\n" +
                    "          id:\"login_container\",//div的id\n" +
                    "          appid: \"" + appid + "\",\n" +
                    "          scope: \"snsapi_login\",//写死\n" +
                    "          redirect_uri: '" + baseUrl + "',\n" +
                    "          state: \"" + AppUtil.getLoginUserCompanyNo() + "\",\n" +
                    "          style: \"black\",\n" +
                    "          href: \"data:text/css;base64,LmltcG93ZXJCb3ggLnFyY29kZXsKICB3aWR0aDogMTgwcHghaW1wb3J0YW50Owp9Ci5pbXBvd2VyQm94IC5pbmZvIHsKICB3aWR0aDogMjgwcHg7CiAgbWFyZ2luOiAwIGF1dG87CiAgbWFyZ2luLWxlZnQ6IC0zMHB4IWltcG9ydGFudDsKICBwYWRkaW5nLWxlZnQ6IDEwcHg7Cn0KLndycF9jb2RlIHsKICBtYXJnaW4tbGVmdDogLTYwcHg7Cn0KLmltcG93ZXJCb3ggLnRpdGxlewogIG1hcmdpbi1sZWZ0OiAtNjBweDsKfQ==\",\n" +
                    "      });\n" +
                    "    </script>\n" +
                    "</body>\n" +
                    "</html>\n";
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(str);
            writer.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
