package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.AuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author angus
 * @date 2018/8/6
 */
@Service
public class AuthRoleServiceImpl implements AuthRoleService {
    @Autowired
    private AuthRoleDOMapperExt authRoleDOMapper;

    @Override
    public void addAuthRole(AuthRoleDO authRoleDO) {
        try {
            authRoleDOMapper.insertSelective(authRoleDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("角色新增失败！");
        }
    }
}
