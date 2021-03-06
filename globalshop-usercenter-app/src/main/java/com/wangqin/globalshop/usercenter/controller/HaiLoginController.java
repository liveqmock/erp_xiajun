package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CookieUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.usercenter.constant.SysConstants;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhulu
 */
@Controller
public class HaiLoginController extends BaseController {

    @Value("#{sys.SESSION_ID}")
    private String SESSION_ID;
    @Value("#{sys.COMPANY_NO}")
    private String COMPANY_NO;
    @Value("#{sys.TIMEOUT}")
    private long TIMEOUT;
    @Autowired
    IUserService userService;

    @Autowired
    Cache loginCache;

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return {Object}
     */
    @PostMapping({"/haiLogin/login", "/login"})
//    @RequestMapping("/login")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object loginPost(HttpServletRequest request, String username, String password, String captcha,
                            @RequestParam(value = "rememberMe", defaultValue = "1") Integer rememberMe) {
        logger.info("POST请求登录");
        // 改为全部抛出异常，避免ajax csrf token被刷新
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("密码不能为空");
        }
        UserVo userVo = new UserVo();
        userVo.setLoginName(username);
        AuthUserDO userDOList = userService.selectSecureByLoginName(username);
        if (userDOList == null) {
            return renderError("该用户不存在");
        } else if (!userDOList.getStatus().equals(SysConstants.USER_DEACTIVED)) {
            return renderError("该用户已停用");
        }else if (!userDOList.getPassword().equals(Md5Util.getMD5(password))) {
            return renderError("该用户密码不正确");
        } else {
            //只会有一个，多了需要检查数据库约束
            AuthUserDO user = userDOList;
            String sessionId = (String) request.getAttribute(SESSION_ID);
            if (StringUtils.isBlank(sessionId)) {
                sessionId = CookieUtil.getCookieValue(request, SESSION_ID);
            }
            loginCache.putEx(sessionId, username, TIMEOUT);
            loginCache.putEx(COMPANY_NO + sessionId, user.getCompanyNo(), TIMEOUT);
            AppUtil.setLoginUser(username, user.getCompanyNo());
            logger.info(username+user.getCompanyNo()+"登录成功");
            return renderSuccess();

        }
    }


//	@RequestMapping("/web")
//	public String agent(Model model) {
//		model.addAttribute("version", "1111");
//		return "agent";
//	}

    /**
     * 退出
     *
     * @return {Result}
     */
    @RequestMapping("/haiLogin/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        logger.info("登出");
        String sessionId = (String) request.getAttribute(SESSION_ID);
        loginCache.remove(sessionId);
        AppUtil.removeLoginUserId();

        loginCache.remove(COMPANY_NO + sessionId);
        AppUtil.removeLoginUserId();
        return renderSuccess();
    }
}
