package com.wangqin.globalshop.usercenter.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.usercenter.service.IUserRoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
	public List<AuthUserRoleDO> selectByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectByUserId(userId);
	}
	
	
	
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
	public void deleteUserRoleByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<AuthUserRoleDO> userRoles = userRoleMapper.selectByUserId(userId);
		for(AuthUserRoleDO userRole : userRoles) {
		    userRoleMapper.deleteByPrimaryKey(userRole.getId());  
			
		}
	
	}



	@Override
	public AuthUserRoleDO selectRoleIdByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectRoleIdByUserId(userId);
	}
	
	//查询某个用户的所有角色的角色Id，@author:xiajun
	@Override
	public List<Long> queryRoleIdListByUserId(Long userId,String companyNo) {
		return userRoleMapper.queryRoleIdListByUserId(userId,companyNo);
	}

}