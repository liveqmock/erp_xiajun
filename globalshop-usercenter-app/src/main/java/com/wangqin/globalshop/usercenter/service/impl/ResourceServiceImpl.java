package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserRoleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthResourceDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleDOMapperExt;
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
    private AuthUserDOMapperExt userMapper;
    @Autowired
    private AuthRoleDOMapperExt roleMapper;
    
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
        return 0;
    }

    @Override
    public AuthResourceDO selectById(Long id) {
        return null;
    }

    @Override
    public int updateSelectiveById(AuthResourceDO resourceDO) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
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

//	@Override
//	public Set<String> queryResUrl(ShiroUser loginName) {
//		Set<String> resCodes = new HashSet<String>();
//		   // shiro中缓存的用户角色
//        //获取角色下所有的资源码
//        //1获取所有的role 的ID
//        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(loginName.getId());
//        //2获取资源的集合
//        List<AuthResourceDO> resourceLists = roleMapper.selectResourceUrlByRoleIdList(roleIdList);
//        if (resourceLists == null) {
//            return resCodes;
//        }
//
//        for (AuthResourceDO resource : resourceLists) {
//        	if(StringUtils.isNotEmpty(resource.getUrl())){
//        		resCodes.add(resource.getUrl());
//        	}
//        }
//		return resCodes;
//	}

    @Override
    public List<AuthResourceDO> queryResourceList()
    {
//        EntityWrapper<AuthResourceDO> wrapper = new EntityWrapper<AuthResourceDO>();
//        wrapper.orderBy("seq");
//        return resourceMapper.selectList(wrapper);
        return resourceMapper.selectList();
    }

    @Override
    public List<AuthResourceDO> queryResourceTree(Long pid) {
        List<AuthResourceDO> listParent = queryResourceByPid(pid);

//        if ((null != listParent) && (listParent.size() > 0)) {
//
//            listParent.forEach(resourceHai -> {
//                resourceHai.setChildList(queryResourceTree(resourceHai.getId()));
//            });
//        }

        return listParent;
    }

    /**
     * 递归调用获取子级资源
     * @param pid
     * @return
     */
    private List<AuthResourceDO> queryResourceByPid(Long pid) {
//        EntityWrapper<AuthResourceDO> wrapper = new EntityWrapper<AuthResourceDO>();
//        AuthResourceDO resource = new AuthResourceDO();
//        wrapper.setEntity(resource);
//        if (null == pid) {
//            wrapper.where("pid is null", pid);
//        } else {
//            wrapper.where("pid = {0}", pid);
//        }
//        wrapper.orderBy("seq");
//        return resourceMapper.selectList(wrapper);
        return resourceMapper.selectList();
    }
}