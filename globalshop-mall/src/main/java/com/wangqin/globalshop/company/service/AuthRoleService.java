package com.wangqin.globalshop.company.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;

/**
 * 角色操作对应的 service
 *
 * @author angus
 * @date 2018/8/6
 */
public interface AuthRoleService {
    /**
     * 新增角色
     *
     * @param authRoleDO
     */
    void addAuthRole(AuthRoleDO authRoleDO);
}
