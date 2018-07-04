package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ResourceQueryVO;
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
    List<RoleQueryVO> selectList();

    Map<String, Set<String>> selectResourceMapByUserId(String loginName);

    List<ResourceQueryVO> selectResourceListByRoleIdList(List<Long> roleIdList);

    List<Long> selectResourceIdListByRoleId(Long id);

    List<Long> queryResourceIdListByRoleId(@Param("id") Long id);


    Integer queryRolesCount(RoleQueryVO roleVO);

    List<RoleQueryVO> queryRoleQueryList(RoleQueryVO roleVO);

    RoleQueryVO searchAuthRole(RoleQueryVO roleVO);

    Long searchAuthRoleCount(RoleQueryVO roleVO);

    List<RoleQueryVO> searchAuthRoleList(RoleQueryVO roleVO);

    AuthUserDO selectByNameAndCompanyNo(@Param("newOne") String newOne, @Param("companyNo") String companyNo);
    
    int insertByRoleVo(RoleQueryVO roleVo);
    
    int updateByRoleVo(RoleQueryVO roleVo);
}
