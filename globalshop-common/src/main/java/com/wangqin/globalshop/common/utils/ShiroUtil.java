package com.wangqin.globalshop.common.utils;

import com.wangqin.globalshop.common.shiro.ShiroUser;
import org.apache.shiro.SecurityUtils;


public class ShiroUtil {
	/**
     * 获取当前登录用户对象
     * @return {ShiroUser}
     */
    public static ShiroUser getShiroUser() {
        return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    }
}
