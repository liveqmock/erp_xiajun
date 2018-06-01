package com.wangqin.globalshop.usercenter.service;

import java.util.List;
import java.util.Set;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.common.utils.AppUtil;

/**
 * Resource 表数据服务层接口
 */
public interface IResourceService {//extends ISuperService<AuthResourceDO>

    List<AuthResourceDO> selectAll();

    List<Tree> selectAllMenu();

    List<Tree> selectAllTree();

    List<Tree> selectTree(String loginName);

    int insert(AuthResourceDO resourceDO);

    AuthResourceDO selectById(Long id);

    int updateSelectiveById(AuthResourceDO resourceDO);

    int deleteById(Long id);

    /**
     * 获取当前用户的所有资源码
     *
     * @return
     */
//    Set<String> queryResUrl(String shiroUser);


}