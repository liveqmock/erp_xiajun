package com.wangqin.globalshop.biz1.app.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangqin.globalshop.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CookieUtil;

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
@Slf4j
public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

    private String sessionIDName;

    @Resource
    private Cache  loginCache;

    public static final String COMPANY_NO = "CompanyNO_";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 通过注解方式鉴权
        Boolean isJump = true;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Authenticated authenticate = handlerMethod.getMethodAnnotation(Authenticated.class);
            if (authenticate == null) {
                // 如果该方法没有注解，再看看方法所在的类有没有添加注解
                Authenticated annotationInBean = handlerMethod.getBeanType().getAnnotation(Authenticated.class);
                if (annotationInBean == null) {
                    return true;
                }
                if (annotationInBean.intercept().equals("notIntercept")) {
                    isJump = false;
                }
            } else {
                if (authenticate.intercept().equals("notIntercept")) {
                    isJump = false;
                }
            }
        }
        String sessionId = (String) request.getAttribute(sessionIDName);
        if (sessionId == null) {
            sessionId = CookieUtil.getCookieValue(request, sessionIDName);
        }
        if (StringUtils.isNotBlank(sessionId)) {
            try {
                //redis 缓存尝试取
                String userId = (String) loginCache.get(sessionId);
                String companyNo = (String) loginCache.get(COMPANY_NO+sessionId);
                if (userId != null) {
                    AppUtil.setLoginUser(userId,companyNo);
                    return true;
                }
            }catch (Exception e){
                LogWorker.log(log,"从Cache获取session异常，跳转登录页面","");
                response.setStatus(302);
                return false;
            }
        }
        if (isJump) {
            response.setStatus(302);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        AppUtil.removeLoginUserId();
        super.postHandle(request, response, handler, modelAndView);
    }

    public String getSessionIDName() {
        return sessionIDName;
    }

    public void setSessionIDName(String sessionIDName) {
        this.sessionIDName = sessionIDName;
    }

}
