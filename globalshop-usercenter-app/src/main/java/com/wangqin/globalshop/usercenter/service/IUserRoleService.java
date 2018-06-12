package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;

/**
 *
 * UserRole 表数据服务层接口
 *
 */
public interface IUserRoleService {

	AuthUserRoleDO selectByUserId(Long userId);
	
	void deleteUserRoleByUserId(Long userId);
	
}