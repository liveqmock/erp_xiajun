package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleResourceDOMapper;

public interface AuthRoleResourceDOMapperExt extends AuthRoleResourceDOMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(AuthRoleResourceDO record);
//
//    int insertSelective(AuthRoleResourceDO record);
//
//    AuthRoleResourceDO selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AuthRoleResourceDO record);
//
//    int updateByPrimaryKey(AuthRoleResourceDO record);

    //Here are
    int deleteSelective(Long roleId);
    
    List<AuthRoleResourceDO> queryRoleResourceByCompanyNo(String companyNo);

}