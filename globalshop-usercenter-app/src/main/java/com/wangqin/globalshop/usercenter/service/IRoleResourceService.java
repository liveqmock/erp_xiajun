package com.wangqin.globalshop.usercenter.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;

/**
 *
 * RoleResource 表数据服务层接口
 *
 */
public interface IRoleResourceService { //extends ISuperService<RoleResource>

	List<AuthRoleResourceDO> queryRoleResourceByCompanyNo(String companyNo);
}