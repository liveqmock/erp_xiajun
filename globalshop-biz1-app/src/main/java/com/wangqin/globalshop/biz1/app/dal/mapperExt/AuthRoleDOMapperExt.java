package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleDOMapper;
import com.wangqin.globalshop.biz1.app.vo.RoleQueryVO;
import org.apache.ibatis.annotations.Param;

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

    List<Long> queryResourceIdListByRoleId(@Param("id") Long id);


    Integer queryRolesCount(RoleQueryVO roleQueryVO);

    List<AuthRoleDO> queryRoleQueryList(RoleQueryVO roleQueryVO);

    AuthRoleDO searchAuthRole(AuthRoleDO authRole);

    Long searchAuthRoleCount(AuthRoleDO authRole);

    List<AuthRoleDO> searchAuthRoleList(AuthRoleDO authRoleDO);

    AuthRoleDO selectByNameAndCompanyNo(String newOne, String companyNo);
}
