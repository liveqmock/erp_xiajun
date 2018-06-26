package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleResourceDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleResourceDOMapperExt;
import com.wangqin.globalshop.usercenter.service.IRoleResourceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * RoleResource 表数据服务层接口实现类
 *
 */
@Service
public class RoleResourceServiceImpl implements IRoleResourceService {
	@Autowired
	private AuthRoleResourceDOMapperExt roleResourceMapper;
	@Override
	public List<AuthRoleResourceDO> queryRoleResourceByCompanyNo(String companyNo) {
		// TODO Auto-generated method stub
		return roleResourceMapper.queryRoleResourceByCompanyNo(companyNo);
	}
	@Override
	public List<AuthRoleResourceDO> selectRoleResourceByResourceId(Long resourceId) {
		// TODO Auto-generated method stub
		return roleResourceMapper.selectRoleResourceByResourceId(resourceId);
	}

	
}