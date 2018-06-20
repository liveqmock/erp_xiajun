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

    private static long TIMEOUT = 30 * 60 * 1000;

    @Autowired
    private IUserService userService;
    @Autowired
    private Cache loginCache;


    @RequestMapping("/login")
    public Object getXcxCookieId(HttpServletRequest request, HttpServletResponse response, String code) {
        JsonResult<List<AuthUserDO>> result = new JsonResult<>();
        String appsecret = "e9cb888962d848456af2048699316e77";
        String appid = "wxfcdeefc831b3e8c4";
        /**获取openId和token*/
        JSONObject o = HttpClientUtil.post("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code", null,
                null, "2");
        /**如果相应包含errcode  表示请求失败*/
        if (o.containsKey("errcode")) {
            return result.buildIsSuccess(false).buildMsg(o.getString("errmsg"));
        }
        //        正确的返回
//        {
//            "access_token":"ACCESS_TOKEN",                    接口调用凭证
//                "expires_in":7200,                            access_token接口调用凭证超时时间，单位（秒）
//                "refresh_token":"REFRESH_TOKEN",              用户刷新access_token
//                "openid":"OPENID",                            授权用户唯一标识
//                "scope":"SCOPE",                              用户授权的作用域，使用逗号（,）分隔
//                "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"    当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
//        }
//        错误的返回
//        {"errcode":40029,"errmsg":"invalid code"}
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
            return result.buildIsSuccess(true).buildMsg("登陆成功");
        }

        return result.buildIsSuccess(false).buildMsg("您还不是本平台的用户,请联系公司管理员进行授权后登陆");

    }

    @RequestMapping("/auth")
    public Object getXcxCookieId(String code, String state) {
        JsonResult<List<AuthUserDO>> result = new JsonResult<>();
        String appsecret = "e9cb888962d848456af2048699316e77";
        String appid = "wxfcdeefc831b3e8c4";
        /**获取openId和token*/
        JSONObject o = HttpClientUtil.post("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code", null,
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
        user.setAvatarUrl(o.getString("headimgurl"));
        userService.addUserByqrcode(state, user);
        return null;
    }

    private boolean hasAuthUser(List<AuthUserDO> list, String state) {
        for (AuthUserDO authUserDO : list) {
            if (state.equals(authUserDO.getCompanyNo())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String encode = URLEncoder.encode("https://test.buyer007.cn/wechatLogin/auth");
        System.out.println(encode);
    }
}
