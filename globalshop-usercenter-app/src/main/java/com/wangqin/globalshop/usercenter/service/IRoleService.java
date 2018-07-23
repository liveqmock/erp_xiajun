package com.wangqin.globalshop.usercenter.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.vo.RoleQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.PageInfo;

import java.util.List;
import java.util.Set;

/**
 * Role 表数据服务层接口
 */
public interface IRoleService {//extends ISuperService<AuthRoleDO>

    void selectDataGrid(PageInfo pageInfo);

    Object selectTree();

    List<Long> selectResourceIdListByRoleId(Long id);

    void updateRoleResource(Long roleId, String resourceIds);

//    Map<String, Set<String>> selectResourceMapByUserId(Long userId);

    Set<String> queryUserResCodes(String userNo);

    int insert(AuthRoleDO roleDO);

    int deleteById(Long id);

    AuthRoleDO selectById(Long id);

    int updateSelectiveById(AuthRoleDO role);

    JsonPageResult<List<RoleQueryVO>> queryRoleList(RoleQueryVO roleVO);
    
    int insertByRoleVo(RoleQueryVO roleVo);
    
    int updateByRoleVo(RoleQueryVO roleVo);
    
    //根据id查询角色的名字，@author:xiajun
    String queryRoleNameByIdOrRoleId(Long roleId,String companyNo);
}