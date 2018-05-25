package com.wangqin.globalshop.biz1.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.service.AuthenticateService;
import com.wangqin.globalshop.common.result.Cache;


@Service("authenticateService")
public class AuthenticateServiceImpl implements AuthenticateService {

    @Resource
    Cache loginCache;

    public Long checkAuth(String sessionId) {
        return (Long) loginCache.get(sessionId);
    }
}
