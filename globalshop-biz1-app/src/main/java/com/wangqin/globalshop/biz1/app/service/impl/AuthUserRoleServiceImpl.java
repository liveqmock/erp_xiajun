package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserRoleMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRole;
import com.wangqin.globalshop.biz1.app.service.IAuthUserRoleService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * AuthUserRole 表数据服务层接口实现类
 *
 */
@Service
public class AuthUserRoleServiceImpl extends SuperServiceImpl<AuthUserRoleMapper, AuthUserRole> implements IAuthUserRoleService {


}