package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleResourceDOMapper;

public interface AuthRoleResourceDOMapperExt extends AuthRoleResourceDOMapper {
    //Here are
    List<AuthRoleResourceDO> selectRoleResourceByResourceId(Long resourceId);	
    
    List<AuthRoleResourceDO> queryRoleResourceByCompanyNo(String companyNo);
    
    int deleteSelective(AuthRoleResourceDO roleResource);
    
    int deleteByRoleId(Long roleId);

}