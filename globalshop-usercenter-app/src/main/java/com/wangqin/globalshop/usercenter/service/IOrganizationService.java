package com.wangqin.globalshop.usercenter.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.usercenter.model.Organization;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface IOrganizationService extends ISuperService<Organization> {

    List<Tree> selectTree();

    List<Organization> selectTreeGrid();

}