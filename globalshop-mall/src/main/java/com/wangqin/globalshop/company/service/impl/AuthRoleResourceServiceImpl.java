package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleResourceDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.AuthRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author angus
 * @date 2018/8/6
 */
@Service
public class AuthRoleResourceServiceImpl implements AuthRoleResourceService {

    @Autowired
    private AuthRoleResourceDOMapperExt authRoleResourceDOMapper;

    @Override
    public void addAuthRoleResource(AuthRoleResourceDO authRoleResourceDO) {
        try {
            authRoleResourceDOMapper.insertSelective(authRoleResourceDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("角色资源新增失败！");
        }
    }
}
