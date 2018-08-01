package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserRoleDOMapper;
import org.apache.ibatis.annotations.Param;

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
    
    //查询某个用户的所有角色的角色Id，@author:xiajun
    List<Long> queryRoleIdListByUserId(@Param("userId")Long userId,@Param("companyNo")String companyNo);
    
    List<AuthUserRoleDO> selectByUserId(Long userId);
    
    void insertByNoId(AuthUserRoleDO record);

    
    AuthUserRoleDO selectRoleIdByUserId(Long userId);



    AuthUserRoleDO searchUserRole(AuthUserRoleDO record);

    Long searchUserRoleCount(AuthUserRoleDO record);

    List<AuthUserRoleDO> searchUserRoleList(AuthUserRoleDO record);

    void deleteRoleByUserId(@Param("userId") Long userId);

    /**
     * 找出该用户的所有角色id
     * @param id 用户id
     * @return
     */
    List<Long> selectAllRole(Long id);

    void deleteByUserIdAndRoleId(@Param("userId")Long userId, @Param("roleId")Long roleId);
}
