package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResource;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthResourceMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserRoleMapper;
import com.wangqin.globalshop.biz1.app.service.IAuthResourceService;
import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 *
 * Resource 表数据服务层接口实现类
 *
 */
@Service
public class ResourceServiceImpl extends SuperServiceImpl<AuthResourceMapper, AuthResource> implements IAuthResourceService {
    private static final int RESOURCE_MENU = 0; // 菜单

    @Autowired
    private AuthResourceMapper resourceMapper;
    @Autowired
    private AuthUserRoleMapper userRoleMapper;
    @Autowired
    private AuthRoleMapper roleMapper;
    
    @Override
    public List<AuthResource> selectAll() {
        EntityWrapper<AuthResource> wrapper = new EntityWrapper<AuthResource>();
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    public List<AuthResource> selectByType(Integer type) {
        EntityWrapper<AuthResource> wrapper = new EntityWrapper<AuthResource>();
        AuthResource AuthResource = new AuthResource();
        wrapper.setEntity(resource);
        wrapper.addFilter("resource_type = {0}", type);
        wrapper.orderBy("seq");
        return resourceMapper.selectList(wrapper);
    }
    
    @Override
    public List<Tree> selectAllMenu() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有菜单
        List<AuthResource> resources = this.selectByType(RESOURCE_MENU);
        if (resources == null) {
            return trees;
        }
        for (AuthResource resource : resources) {
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
        List<AuthResource> resources = this.selectAll();
        if (resources == null) {
            return trees;
        }
        for (AuthResource resource : resources) {
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
    public List<Tree> selectTree(ShiroUser shiroUser) {
        List<Tree> trees = new ArrayList<Tree>();
        // shiro中缓存的用户角色
        Set<String> roles = shiroUser.getRoles();
        if (roles == null) {
            return trees;
        }
        // 如果有超级管理员权限
        if (roles.contains("admin")) {
            List<AuthResource> resourceList = this.selectByType(RESOURCE_MENU);
            if (resourceList == null) {
                return trees;
            }
            for (AuthResource resource : resourceList) {
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
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        if (roleIdList == null) {
            return trees;
        }
        List<AuthResource> resourceLists = roleMapper.selectResourceListByRoleIdList(roleIdList);
        if (resourceLists == null) {
            return trees;
        }
        for (AuthResource resource : resourceLists) {
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
	public Set<String> queryResUrl(ShiroUser shiroUser) {
		Set<String> resCodes = new HashSet<String>();
		   // shiro中缓存的用户角色
        //获取角色下所有的资源码
        //1获取所有的role 的ID
        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(shiroUser.getId());
        //2获取资源的集合
        List<AuthResource> resourceLists = roleMapper.selectResourceUrlByRoleIdList(roleIdList);
        if (resourceLists == null) {
            return resCodes;
        }
        
        for (AuthResource resource : resourceLists) {
        	if(StringUtils.isNotEmpty(resource.getUrl())){
        		resCodes.add(resource.getUrl());
        	}
        }
		return resCodes;
	}

    
}