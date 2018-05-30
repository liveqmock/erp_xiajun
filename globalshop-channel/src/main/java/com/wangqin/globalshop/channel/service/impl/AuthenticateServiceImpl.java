package com.wangqin.globalshop.channel.service.impl;

import com.wangqin.globalshop.biz1.app.service.AuthenticateService;
import com.wangqin.globalshop.common.result.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("authenticateService")
public class AuthenticateServiceImpl implements AuthenticateService {

    @Resource
    Cache loginCache;

    public Long checkAuth(String sessionId) {
        return (Long) loginCache.get(sessionId);
    }
}
