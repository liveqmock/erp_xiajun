package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleResourceDOMapper;

public interface AuthRoleResourceDOMapperExt extends AuthRoleResourceDOMapper {
    //Here are
    List<AuthRoleResourceDO> selectRoleResourceByResourceId(Long resourceId);
	
	int deleteSelective(Long roleId);
    
    List<AuthRoleResourceDO> queryRoleResourceByCompanyNo(String companyNo);

}