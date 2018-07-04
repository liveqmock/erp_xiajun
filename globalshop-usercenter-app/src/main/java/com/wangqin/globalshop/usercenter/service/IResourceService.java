package com.wangqin.globalshop.usercenter.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.vo.ResourceQueryVO;

import com.wangqin.globalshop.common.result.Tree;


/**
 * Resource 表数据服务层接口
 */
public interface IResourceService {//extends ISuperService<AuthResourceDO>

    List<ResourceQueryVO> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectTree(String loginName);

    int insert(AuthResourceDO resourceDO);

    AuthResourceDO selectById(Long id);

    int updateSelectiveById(AuthResourceDO resourceDO);

    int deleteById(Long id);
    
    List<ResourceQueryVO> queryResource();
    
    AuthResourceDO queryTreeByResourceId(String resourceId);

    int insertByNoId(ResourceQueryVO resouceVo);
    
    ResourceQueryVO queryTreeVoByResourceId(String resourceId);

    int updateByResourceVo(ResourceQueryVO resourceVO);
}