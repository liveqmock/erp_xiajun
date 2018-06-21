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
    public static final String SESSION_ID = "SessionID";
    public static final String COMPANY_NO = "CompanyNO_";
    public static final String APPSECRET = "e9cb888962d848456af2048699316e77";
    public static final String appid = "wxfcdeefc831b3e8c4";
    private static final String sysurl = "http://test.buyer007.cn";

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
                response.sendRedirect("/#/overview");
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
     * 获取微信二维码的链接
     *
     * @param type 授权时 该参数为authorized
     *             登录时  该参数为login
     * @return
     */
    @RequestMapping("/getUrl")
    public Object getUrl(String type) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            String baseUrl = sysurl + "/wechatLogin/" + type;
            baseUrl = URLEncoder.encode(baseUrl, "UTF-8");
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=wxfcdeefc831b3e8c4&redirect_uri=" + baseUrl + "&response_type=code&scope=snsapi_login";
            if ("authorized".equals(type)) {
                String state = AppUtil.getLoginUserCompanyNo();
                url = url + "&state=" + state;
            }
            return result.buildData(url).buildIsSuccess(true);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(false).buildMsg("获取失败");

    }

}