package com.wangqin.globalshop.company.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;

/**
 * 用户角色关系对应 service
 *
 * @author angus
 * @date 2018/8/7
 */
public interface AuthUserRoleService {

    /**
     * 新增用户角色关系对应
     *
     * @param authUserRoleDO
     */
    void addAuthUserRole(AuthUserRoleDO authUserRoleDO);
}
