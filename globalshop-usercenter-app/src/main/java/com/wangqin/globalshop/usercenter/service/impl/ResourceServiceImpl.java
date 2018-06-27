package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthResourceDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleResourceDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.usercenter.service.IResourceService;

/**
 *
 * AuthResourceDO 表数据服务层接口实现类
 *
 */
@Service
public class ResourceServiceImpl  implements IResourceService {//extends SuperServiceImpl<AuthResourceDOMapperExt, AuthResourceDO>
    private static final int RESOURCE_MENU = 0; // 菜单

    @Autowired
    private AuthResourceDOMapperExt resourceMapper;
    @Autowired
    private AuthUserRoleDOMapperExt userRoleMapper;
    @Autowired
    private AuthRoleDOMapperExt roleMapper;
    @Autowired
    private AuthRoleResourceDOMapperExt roleResource;
    
    @Override
    public List<AuthResourceDO> selectAll() {
//        EntityWrapper<AuthResourceDO> wrapper = new EntityWrapper<AuthResourceDO>();
//        wrapper.orderBy("seq");

        return resourceMapper.selectList();
    }
    
    public List<AuthResourceDO> selectByType(Integer type) {
//        EntityWrapper<AuthResourceDO> wrapper = new EntityWrapper<AuthResourceDO>();
//        AuthResourceDO resource = new AuthResourceDO();
//        wrapper.setEntity(resource);
//        wrapper.addFilter("resource_type = {0}", type);
//        wrapper.orderBy("seq");
        return resourceMapper.selectList();
    }

    @Override
    public int insert(AuthResourceDO resourceDO) {
    	resourceDO.init();
        return resourceMapper.insertSelective(resourceDO);
    }

    @Override
    public AuthResourceDO selectById(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateSelectiveById(AuthResourceDO resourceDO) {
        return resourceMapper.updateByPrimaryKeySelective(resourceDO);
    }

    @Override
    public int deleteById(Long id) {
//    	AuthResourceDO resource = resourceMapper.selectByPrimaryKey(id);
//    	List<AuthRoleResourceDO> authRoleResourcdeList = roleResource.selectRoleResourceByResourceId(Long.parseLong(resource.getResourceId()));
//        for(AuthRoleResourceDO authRoleResourcde : authRoleResourcdeList) {
//        	Long roleresourceId = authRoleResourcde.getId();
//        	roleResource.deleteByPrimaryKey(roleresourceId);
//        }
    	
    	return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Tree> selectAllMenu() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有菜单
        List<AuthResourceDO> resources = this.selectByType(RESOURCE_MENU);
        if (resources == null) {
            return trees;
        }
        for (AuthResourceDO resource : resources) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            trees.add(tree);
        }
        return trees;
    }
    
    @Override
    public List<Tree> selectAllTree() {
        // 获取所有的资源 tree形式，展示
        List<Tree> trees = new ArrayList<Tree>();
        List<AuthResourceDO> resources = this.selectAll();
        if (resources == null) {
            return trees;
        }
        for (AuthResourceDO resource : resources) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            trees.add(tree);
        }
        return trees;
    }
    
    @Override
    public List<Tree> selectTree(String loginName) {
        List<Tree> trees = new ArrayList<Tree>();
        // shiro中缓存的用户角色
        List<AuthUserRoleDO> roles = userRoleMapper.selectByLoginName(loginName);//userMapper.selectByLoginName(loginName).;

        if (roles == null) {
            return trees;
        }
        // 如果有超级管理员权限
        if (roles.contains("admin")) {
            List<AuthResourceDO> resourceList = this.selectByType(RESOURCE_MENU);
            if (resourceList == null) {
                return trees;
            }
            for (AuthResourceDO resource : resourceList) {
                Tree tree = new Tree();
                tree.setId(resource.getId());
                tree.setPid(resource.getPid());
                tree.setText(resource.getName());
                tree.setIconCls(resource.getIcon());
                tree.setAttributes(resource.getUrl());
                tree.setOpenMode(resource.getOpenMode());
                trees.add(tree);
            }
            return trees;
        }
        // 普通用户
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(loginName);
        if (roleIdList == null) {
            return trees;
        }
        List<AuthResourceDO> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
        if (resourceLists == null) {
            return trees;
        }
        for (AuthResourceDO resource : resourceLists) {
            Tree tree = new Tree();
            tree.setId(resource.getId());
            tree.setPid(resource.getPid());
            tree.setText(resource.getName());
            tree.setIconCls(resource.getIcon());
            tree.setAttributes(resource.getUrl());
            tree.setOpenMode(resource.getOpenMode());
            trees.add(tree);
        }
        return trees;
    }

	@Override
	public List<AuthResourceDO> queryResource() {
		// TODO Auto-generated method stub
		return resourceMapper.queryResource();
	}

	@Override
	public AuthResourceDO queryTreeByResourceId(String resourceId) {
		// TODO Auto-generated method stub
		return resourceMapper.queryTreeByResourceId(resourceId);
	}



}