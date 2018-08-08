package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.AuthUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author angus
 * @date 2018/8/7
 */
@Service
public class AuthUserRoleServiceImpl implements AuthUserRoleService {
    @Autowired
    private AuthUserRoleDOMapperExt authUserRoleDOMapper;

    @Override
    public void addAuthUserRole(AuthUserRoleDO authUserRoleDO) {
        try {
            authUserRoleDOMapper.insertSelective(authUserRoleDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("新增用户角色关系队对应失败！");
        }
    }
}
