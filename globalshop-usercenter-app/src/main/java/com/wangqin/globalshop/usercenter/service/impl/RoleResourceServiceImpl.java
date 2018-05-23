package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResource;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleResourceMapper;
import com.wangqin.globalshop.biz1.app.service.IAuthRoleResourceService;
import com.wangqin.globalshop.usercenter.model.RoleResource;
import com.wangqin.globalshop.usercenter.service.IRoleResourceService;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.usercenter.mapper.RoleResourceMapper;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * RoleResource 表数据服务层接口实现类
 *
 */
@Service
public class RoleResourceServiceImpl extends SuperServiceImpl<AuthRoleResourceMapper, AuthRoleResource> implements IAuthRoleResourceService {


}