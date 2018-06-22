package com.wangqin.globalshop.biz1.app.aop;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CookieUtil;
import com.wangqin.globalshop.common.utils.LogWorker;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 访问鉴权的拦截器，主要是用于需要登录才能访问页面的鉴权
 * <p>
 * 使用方法：</br>
 * 在需要做登录鉴权的url请求controller方法上加入<code>@Authenticated</code>注解，</br>
 * 如果controller的所有方法都要做登录鉴权的话，直接在controller类上加<code>@Authenticated</code>注解即可</br>
 * 后面controller或service需要取得登录者userId的话，可以使用<code>AppUtil.getLoginUserId()</code>
 * 
 * @see Authenticated
 * @see AppUtil
 * @author Sivan
 */
public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

    private String             sessionIDName;

    @Resource
    private Cache              loginCache;

    public static final String COMPANY_NO = "CompanyNO_";
    protected static Logger    log        = LoggerFactory.getLogger("System");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 通过注解方式鉴权
        Boolean isJump = true;
        String uri = request.getRequestURI();
        //设置不拦截的对象
        String[] noFilters = new String[] {  "js",
                "images", "css", "/login" };
        boolean beFilter = true;
        for (String s : noFilters) {
            if (uri.contains(s)) {
                beFilter = false;
                break;
            }
        }
        if (!beFilter) {
            return super.preHandle(request, response, handler);
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authenticated authenticate = handlerMethod.getMethodAnnotation(Authenticated.class);
            if (authenticate == null) {
                // 如果该方法没有注解，再看看方法所在的类有没有添加注解
                Authenticated annotationInBean = handlerMethod.getBeanType().getAnnotation(Authenticated.class);
                if (annotationInBean == null) {
                    return true;
                }
                if ("notIntercept".equals(annotationInBean.intercept())) {
                    isJump = false;
                }
            } else {
                if ("notIntercept".equals(authenticate.intercept())) {
                    isJump = false;
                }
            }
        }
        String sessionId = (String) request.getAttribute(sessionIDName);
        if (sessionId == null) {
            sessionId = CookieUtil.getCookieValue(request, sessionIDName);
        }
        if (StringUtils.isNotBlank(sessionId)) {
            if(!"/login".equalsIgnoreCase(request.getRequestURI())) {
                try {
                    // redis 缓存尝试取
                    String userId = (String) loginCache.get(sessionId);
                    String companyNo = (String) loginCache.get(COMPANY_NO + sessionId);
                    if (userId != null) {
                        AppUtil.setLoginUser(userId, companyNo);
                        return true;
                    }
                } catch (Exception e) {
                    LogWorker.log(log, "从Cache获取session异常，跳转登录页面", "");
                    response.sendRedirect("127.0.0.1:8000/#/login");
                    return false;
                }
            }else
            {
                isJump=false;
            }
        }
        if (isJump) {
            response.sendRedirect("127.0.0.1:8000/#/login");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        AppUtil.removeLoginUserId();
        super.postHandle(request, response, handler, modelAndView);
    }

    public String getSessionIDName() {
        return sessionIDName;
    }

    public void setSessionIDName(String sessionIDName) {
        this.sessionIDName = sessionIDName;
    }

}
