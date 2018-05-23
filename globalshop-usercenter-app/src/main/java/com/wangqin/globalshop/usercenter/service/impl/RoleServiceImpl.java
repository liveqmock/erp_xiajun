package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResource;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRole;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResource;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthRoleResourceMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserRoleMapper;
import com.wangqin.globalshop.biz1.app.service.IAuthRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.common.utils.StringUtils;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends SuperServiceImpl<AuthRoleMapper, AuthRole> implements IAuthRoleService {

    @Autowired
    private AuthRoleMapper authRoleMapper;
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    @Autowired
    private AuthRoleResourceMapper authRoleResourceMapper;
    
    public List<AuthRole> selectAll() {
        EntityWrapper<AuthRole> wrapper = new EntityWrapper<AuthRole>();
        wrapper.orderBy("seq");
        return authRoleMapper.selectList(wrapper);
    }
    
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<AuthRole> page = new Page<AuthRole>(pageInfo.getNowpage(), pageInfo.getSize());
        List<AuthRole> list = authRoleMapper.selectRoleList(page, pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public Object selectTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<AuthRole> roles = this.selectAll();
        for (AuthRole role : roles) {
            Tree tree = new Tree();
            tree.setId(role.getId());
            tree.setText(role.getName());

            trees.add(tree);
        }
        return trees;
    }

    @Override
    public void updateRoleResource(Long roleId, String resourceIds) {
        // 先删除后添加,有点爆力
        AuthRoleResource roleResource = new AuthRoleResource();
        roleResource.setRoleId(roleId);
        authRoleResourceMapper.deleteSelective(roleResource);
        
        String[] resourceIdArray = resourceIds.split(",");
        for (String resourceId : resourceIdArray) {
            roleResource = new AuthRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Long.parseLong(resourceId));
            authRoleResourceMapper.insert(roleResource);
        }
    }

    @Override
    public List<Long> selectResourceIdListByRoleId(Long id) {
        return authRoleMapper.selectResourceIdListByRoleId(id);
    }
    
    @Override
    public Map<String, Set<String>> selectResourceMapByUserId(Long userId) {
        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
        List<Long> roleIdList = authUserRoleMapper.selectRoleIdListByUserId(userId);
        Set<String> urlSet = new HashSet<String>();
        Set<String> roles = new HashSet<String>();
        Set<String> resCodes = new HashSet<String>();
        for (Long roleId : roleIdList) {
            List<AuthResource> resourceList = authRoleMapper.selectResourceListByRoleId(roleId);
            if (resourceList != null) {
				for (AuthResource resource : resourceList) {
					if (resource != null) {
						if (StringUtils.isNotBlank(resource.getUrl())) {
							urlSet.add(resource.getUrl());
						}
						if (StringUtils.isNotBlank(resource.getResCode())) {
							resCodes.add(resource.getResCode());
						}
					}
				}
            }
            AuthRole role = authRoleMapper.selectById(roleId);
            if (role != null) {
                roles.add(role.getName());
            }
        }
        resourceMap.put("urls", urlSet);
        resourceMap.put("roles", roles);
        resourceMap.put("resCodes", resCodes);
        return resourceMap;
    }

}