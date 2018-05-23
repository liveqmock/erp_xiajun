package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRole;
import com.wangqin.globalshop.biz1.app.service.IAuthRoleService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * AuthRole 表数据服务层接口实现类
 *
 */
@Service
public class AuthRoleServiceImpl extends SuperServiceImpl<AuthRoleMapper, AuthRole> implements IAuthRoleService {


}