package com.wangqin.globalshop.usercenter.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.common.utils.PageInfo;

/**
 * Role 表数据服务层接口
 */
public interface IRoleService {//extends ISuperService<AuthRoleDO>

    void selectDataGrid(PageInfo pageInfo);

    Object selectTree();

    List<Long> selectResourceIdListByRoleId(Long id);

    void updateRoleResource(Long id, String resourceIds);

//    Map<String, Set<String>> selectResourceMapByUserId(Long userId);

    Set<String> queryUserResCodes(String userNo);

    void insert(AuthRoleDO role);

    void deleteById(Long id);

    AuthRoleDO selectById(Long id);

    void updateSelectiveById(AuthRoleDO role);

}