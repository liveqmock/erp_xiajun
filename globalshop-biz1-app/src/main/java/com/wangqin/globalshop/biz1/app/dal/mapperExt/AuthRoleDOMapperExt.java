package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleDOMapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AuthRoleDOMapperExt extends AuthRoleDOMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(AuthRoleDO record);
//
//    int insertSelective(AuthRoleDO record);
//
//    AuthRoleDO selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AuthRoleDO record);
//
//    int updateByPrimaryKey(AuthRoleDO record);

//    List<AuthRoleDO> selectRoleList(page, pageInfo.getSort(), pageInfo.getOrder());

    //Here are
    List<AuthRoleDO> selectList();

    Map<String, Set<String>> selectResourceMapByUserId(String loginName);

    List<AuthResourceDO> selectResourceListByRoleIdList(List<Long> roleIdList );

    List<Long>  selectResourceIdListByRoleId( Long id);

}