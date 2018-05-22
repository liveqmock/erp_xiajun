package com.wangqin.globalshop.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.model.Role;

/**
 *
 * Role 表数据服务层接口
 *
 */
public interface IRoleService extends ISuperService<Role> {

    void selectDataGrid(PageInfo pageInfo);

    Object selectTree();

    List<Long> selectResourceIdListByRoleId(Long id);

    void updateRoleResource(Long id, String resourceIds);

    Map<String, Set<String>> selectResourceMapByUserId(Long userId);

}