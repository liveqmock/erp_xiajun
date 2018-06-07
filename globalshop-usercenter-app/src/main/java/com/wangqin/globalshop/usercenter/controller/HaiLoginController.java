package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.csrf.CsrfToken;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.usercenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author zhulu
 *
 */
@Controller
public class HaiLoginController extends BaseController {


    public static final String SESSION_ID = "SessionID";

    public static final String COMPANY_NO = "CompanyNO_";

    private static long  TIMEOUT=30*60*1000;
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
//    @PostMapping("/haiLogin")
	@RequestMapping(value = "/haiLogin/login",method = RequestMethod.POST)
    @CsrfToken(remove = true)
    @ResponseBody
    public Object loginPost(HttpServletRequest request, String username, String password,
                            @RequestParam(value = "rememberMe", defaultValue = "1") Integer rememberMe) {
        logger.info("POST请求登录");
        JsonResult<Object> jsonResult = new JsonResult<>();
        return jsonResult.buildIsSuccess(true);
//        // 改为全部抛出异常，避免ajax csrf token被刷新
//        if (StringUtils.isBlank(username)) {
//            throw new RuntimeException("用户名不能为空");
//        }
//        if (StringUtils.isBlank(password)) {
//            throw new RuntimeException("密码不能为空");
//        }
//        Subject user = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        // 设置记住密码
//        token.setRememberMe(1 == rememberMe);
//        JsonResult<String> result = new JsonResult<>();
//        try {
//            user.login(token);
//            return result.buildIsSuccess(true);
//        } catch (UnknownAccountException e) {
//        	e.printStackTrace();
//        	return result.buildData("账号不存在！").buildIsSuccess(false);
////            throw new RuntimeException("账号不存在！", e);
//        } catch (DisabledAccountException e) {
////            throw new RuntimeException("账号未启用！", e);
//        	e.printStackTrace();
//        	return result.buildData("账号未启用！").buildIsSuccess(false);
//        } catch (IncorrectCredentialsException e) {
////            throw new RuntimeException("密码错误！", e);
//            e.printStackTrace();
//        	return result.buildData("密码错误！").buildIsSuccess(false);
//        } catch (Throwable e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
    }
	
	
	
//	@RequestMapping("/web")
//	public String agent(Model model) {
//		model.addAttribute("version", "1111");
//		return "agent";
//	}
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
        AppUtil.removeLoginUserId();
        return renderSuccess();
    }
}
