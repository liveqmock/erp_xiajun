package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserRoleDOMapper;

import java.util.List;

public interface AuthUserRoleDOMapperExt extends AuthUserRoleDOMapper {
    //    int deleteByPrimaryKey(Long id);
//
//    int insert(AuthUserRoleDO record);
//
//    int insertSelective(AuthUserRoleDO record);
//
//    AuthUserRoleDO selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AuthUserRoleDO record);
//
//    int updateByPrimaryKey(AuthUserRoleDO record);

    //Here are
    List<AuthUserRoleDO> selectByLoginName(String loginName);

    List<Long> selectRoleIdListByUserId(String loginName);
    
    AuthUserRoleDO selectByUserId(Long userId);
    
    void insertByNoId(AuthUserRoleDO record);
}