package com.wangqin.globalshop.usercenter.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;

/**
 *
 * UserRole 表数据服务层接口
 *
 */
public interface IUserRoleService {

	List<AuthUserRoleDO> selectByUserId(Long userId);
	
	void deleteUserRoleByUserId(Long userId);
	
}