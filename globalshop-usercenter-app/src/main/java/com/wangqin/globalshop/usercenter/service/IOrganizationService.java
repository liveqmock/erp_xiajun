package com.wangqin.globalshop.usercenter.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.globalshop.common.result.Tree;
//import com.wangqin.globalshop.usercenter.app.commons.result.Tree;

/**
 *
 * Organization 表数据服务层接口
 *
 */
public interface IOrganizationService {//extends ISuperService<AuthOrganizationDO>

    List<Tree> selectTree();

    List<AuthOrganizationDO> selectTreeGrid();

    int deleteById(Long id);

    int updateSelectiveById(AuthOrganizationDO organization);

    int insert(AuthOrganizationDO organization);

    AuthOrganizationDO selectById(Long id);

//        organizationService.deleteById(id);
//        organizationService.updateSelectiveById(organization);
//        organizationService.insert(organization);
//    AuthOrganizationDO organization = organizationService.selectById(id);
}