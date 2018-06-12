package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.usercenter.service.IUserRoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {
	
	
	@Autowired
	private AuthUserRoleDOMapperExt userRoleMapper;
	@Override
	public AuthUserRoleDO selectByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectByUserId(userId);
	}
	
	
	
	@Override
	public void deleteUserRoleByUserId(Long userId) {
		// TODO Auto-generated method stub
		AuthUserRoleDO userRole = userRoleMapper.selectByUserId(userId);
	    userRoleMapper.deleteByPrimaryKey(userRole.getId());  
	}

}