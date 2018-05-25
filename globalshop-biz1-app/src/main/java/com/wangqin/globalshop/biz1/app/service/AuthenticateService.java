package com.wangqin.globalshop.biz1.app.service;

/**
 * 类AuthenticateService.java的实现描述：登录鉴权，该接口是提供给登录鉴权的拦截器使用
 * 
 * @author sivanblack 2018年5月25日 下午5:51:16
 */
public interface AuthenticateService {

    Long checkAuth(String sessionId);
}
