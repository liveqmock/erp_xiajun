package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRole;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserRoleMapper;
import com.wangqin.globalshop.biz1.app.service.IAuthUserRoleService;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends SuperServiceImpl<AuthUserRoleMapper, AuthUserRole> implements IAuthUserRoleService {

}