package com.wangqin.globalshop.biz1.app.aop.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wangqin.globalshop.common.utils.CookieUtil;
import com.wangqin.globalshop.common.utils.SessionIDUtil;

public class SessionInterceptor extends HandlerInterceptorAdapter {

    private String domain;
    private String sessionIDName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String sessionId = CookieUtil.getCookieValue(request, sessionIDName);

        if (StringUtils.isBlank(sessionId)) {
            sessionId = SessionIDUtil.generateSessionId();
            CookieUtil.setCookie(response, sessionIDName, sessionId, -1, domain, "/");
        }

        if (sessionId != null) {
            request.setAttribute(sessionIDName, sessionId);
        }
        return true;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSessionIDName() {
        return sessionIDName;
    }

    public void setSessionIDName(String sessionIDName) {
        this.sessionIDName = sessionIDName;
    }

}

