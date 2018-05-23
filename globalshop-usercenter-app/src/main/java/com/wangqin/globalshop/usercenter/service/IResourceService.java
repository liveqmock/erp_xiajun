package com.wangqin.globalshop.usercenter.service;

import java.util.List;
import java.util.Set;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import com.wangqin.globalshop.usercenter.model.Resource;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResourceService extends ISuperService<Resource> {

    List<Resource> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectTree(ShiroUser shiroUser);

    /**
     * 获取当前用户的所有资源码
     * @return
     */
    Set<String> queryResUrl(ShiroUser shiroUser);
}