package com.wangqin.globalshop.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.wangqin.globalshop.common.utils.LogWorker;

/**
 * Created by Patrick on 2018/5/15.
 */
@Component
@WebFilter(filterName = "RequestFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {

    protected static Logger log = LoggerFactory.getLogger("System");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        LogWorker.logStart(log, "请求Filter", "url:{}", servletRequest1.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
        LogWorker.logEnd(log, "请求Filter", "response:{}", servletResponse);

    }

    @Override
    public void destroy() {

    }
}
