package com.wangqin.globalshop.biz1.app.dal.dataVo;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;

public class AuthUserVO extends AuthUserDO{
	
	private String organizationName;
	
	private List<AuthRoleDO> rolesList;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<AuthRoleDO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<AuthRoleDO> rolesList) {
		this.rolesList = rolesList;
	}		
	
}
