package com.wangqin.globalshop.company.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;

/**
 * 用户相关的 service
 *
 * @author angus
 * @date 2018/8/6
 */
public interface AuthUserService {

    /**
     * 新增用户
     *
     * @param authUserDO
     */
    void addAuthUser(AuthUserDO authUserDO);
}
