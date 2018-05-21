package com.wangqin.shopkeeper.web.filter;

import com.wangqin.shopkeeper.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Patrick on 2018/5/15.
 */
@Slf4j
@Component
@WebFilter(filterName = "RequestFilter", urlPatterns = "/*")
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        LogWorker.logStart(log,"请求Filter","url:{}",servletRequest1.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
        LogWorker.logEnd(log,"请求Filter","response:{}",servletResponse);

    }

    @Override
    public void destroy() {

    }
}
