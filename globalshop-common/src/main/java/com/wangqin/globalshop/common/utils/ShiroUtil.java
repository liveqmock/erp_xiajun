package com.wangqin.globalshop.common.utils;

import org.apache.shiro.SecurityUtils;

import com.wangqin.globalshop.common.shiro.ShiroUser;


public class ShiroUtil {
	/**
     * 获取当前登录用户对象
     * @return {ShiroUser}
     */
    public static ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }
}
