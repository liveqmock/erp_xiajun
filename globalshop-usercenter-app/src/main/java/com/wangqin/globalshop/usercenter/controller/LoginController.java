package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.csrf.CsrfToken;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.usercenter.service.IUserService;
import com.wangqin.globalshop.usercenter.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description：登录退出
 */
@Controller
public class LoginController extends BaseController {

    public static final String SESSION_ID = "SessionID";

    public static final String COMPANY_NO = "CompanyNO_";

    private static long  TIMEOUT=30*60*1000;
    @Autowired
    IUserService userService;

    @Autowired
    Cache loginCache;
	/**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
//    @RequestMapping("/w")
    public String index() {
        return "redirect:/web";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/web")
//    @RequestMapping("/web")
    public String index(Model model) {
        return "web";
    }

    /**
     * GET 登录
     * @return {String}
     */
    @GetMapping("/login")
    @CsrfToken(create = true)
    public String login() {
        logger.info("GET请求登录");
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "redirect:/web";
//        }
        return "login";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return {Object}
     */
    @RequestMapping("/login")
//    @CsrfToken(remove = true)
    @ResponseBody
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
//        if (StringUtils.isBlank(captcha)) {
//            throw new RuntimeException("验证码不能为空");
//        }
//        if (!CaptchaUtils.validate(request, captcha)) {
//            throw new RuntimeException("验证码错误");
//        }
//        Subject user = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 设置记住密码
//        token.setRememberMe(1 == rememberMe);
        UserVo userVo=new UserVo();
        userVo.setLoginName(username);
        AuthUserDO userDOList=userService.selectByLoginName(username);
        if(userDOList==null ){
            return renderError("该用户不存在");
        }else{
            //只会有一个，多了需要检查数据库约束
            AuthUserDO user=userDOList;
            String sessionId = (String) request.getAttribute(SESSION_ID);
            if (StringUtils.isNotBlank(sessionId)) {
                loginCache.putEx(sessionId, username, TIMEOUT);
                loginCache.putEx(COMPANY_NO+sessionId,user.getCompanyNo(),TIMEOUT);
                AppUtil.setLoginUserId(username);
                AppUtil.setCompanyNo(user.getCompanyNo());
                return renderSuccess();
            }else {
                return renderError("sessionId不存在");
            }

        }
//        try {
////            user.login(token);
//            return renderSuccess();
//        } catch (UnknownAccountException e) {
//            throw new RuntimeException("账号不存在！", e);
//        } catch (DisabledAccountException e) {
//            throw new RuntimeException("账号未启用！", e);
//        } catch (IncorrectCredentialsException e) {
//            throw new RuntimeException("密码错误！", e);
//        } catch (Throwable e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
    }

    /**
     * 未授权
     * @return {String}
     */
//    @GetMapping("/unauth")
//    public String unauth() {
//        if (SecurityUtils.getSubject().isAuthenticated() == false) {
//            return "redirect:/login";
//        }
//        return "unauth";
//    }

    /**
     * 退出
     * @return {Result}
     */
    @PostMapping("/logout")
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        logger.info("登出");
        String sessionId = (String) request.getAttribute(SESSION_ID);
        loginCache.remove(sessionId);
        AppUtil.removeLoginUserId();

        loginCache.remove(COMPANY_NO+sessionId);
        AppUtil.removeCompanyNo();
        return renderSuccess();
    }

}
