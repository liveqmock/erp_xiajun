package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthRoleResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthRoleResourceDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserRoleDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.RoleQueryVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.usercenter.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.common.utils.PageInfo;
import com.wangqin.globalshop.common.utils.StringUtils;

/**
 *
 * AuthRoleDO 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private AuthRoleDOMapperExt roleMapper;
    @Autowired
    private AuthUserRoleDOMapperExt userRoleMapper;
    @Autowired
    private AuthRoleResourceDOMapperExt roleResourceMapper;
    
    public List<AuthRoleDO> selectAll() {
//        EntityWrapper<AuthRoleDO> wrapper = new EntityWrapper<AuthRoleDO>();
//        wrapper.orderBy("seq");
         return roleMapper.selectList();
    }
    
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
//        Page<AuthRoleDO> page = new Page<AuthRoleDO>(pageInfo.getNowpage(), pageInfo.getSize());
//        List<AuthRoleDO> list = roleMapper.selectRoleList(page, pageInfo.getSort(), pageInfo.getOrder());
//        pageInfo.setRows(list);
//        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public int insert(AuthRoleDO role) {
        role.init();
        role.setIsDel(true);
        return roleMapper.insert(role);

    }

    @Override
    public int deleteById(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public AuthRoleDO selectById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateSelectiveById(AuthRoleDO role) {
        role.init();
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public Set<String> queryUserResCodes(String loginName) {
        Map<String, Set<String>> resourceMap = roleMapper.selectResourceMapByUserId(loginName);
        Set<String> urls = resourceMap.get("urls");
        Set<String> roles = resourceMap.get("roles");
        Set<String> resCodes = resourceMap.get("resCodes");
        return resCodes;
    }





    @Override
    public Object selectTree() {
        List<Tree> trees = new ArrayList<Tree>();
        List<AuthRoleDO> roles = this.selectAll();
        for (AuthRoleDO role : roles) {
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
        AuthRoleResourceDO roleResource = new AuthRoleResourceDO();
        roleResource.setRoleId(roleId);
        roleResourceMapper.deleteSelective(roleResource);
        
        String[] resourceIdArray = resourceIds.split(",");
        for (String resourceId : resourceIdArray) {
            roleResource = new AuthRoleResourceDO();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Long.parseLong(resourceId));
            roleResourceMapper.insert(roleResource);
        }
    }

    @Override
    public List<Long> selectResourceIdListByRoleId(Long id) {
        return roleMapper.selectResourceIdListByRoleId(id);
    }
    
//    @Override
//    public Map<String, Set<String>> selectResourceMapByUserId(String userId) {
//        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
//        List<Long> roleIdList = userRoleMapper.selectRoleIdListByUserId(userId);
//        Set<String> urlSet = new HashSet<String>();
//        Set<String> roles = new HashSet<String>();
//        Set<String> resCodes = new HashSet<String>();
//        for (Long roleId : roleIdList) {
//            List<AuthResourceDO> resourceList = roleMapper.selectResourceListByRoleId(roleId);
//            if (resourceList != null) {
//				for (AuthResourceDO resource : resourceList) {
//					if (resource != null) {
//						if (StringUtils.isNotBlank(resource.getUrl())) {
//							urlSet.add(resource.getUrl());
//						}
//						if (StringUtils.isNotBlank(resource.getResCode())) {
//							resCodes.add(resource.getResCode());
//						}
//					}
//				}
//            }
//            AuthRoleDO role = roleMapper.selectById(roleId);
//            if (role != null) {
//                roles.add(role.getName());
//            }
//        }
//        resourceMap.put("urls", urlSet);
//        resourceMap.put("roles", roles);
//        resourceMap.put("resCodes", resCodes);
//        return resourceMap;
//    }


    @Override
    public JsonPageResult<List<AuthRoleDO>> queryRoleList(RoleQueryVO roleQueryVO) {
        JsonPageResult<List<AuthRoleDO>> result = new JsonPageResult<>();

        Integer totalCount = roleMapper.queryRolesCount(roleQueryVO);

        if ((null != totalCount) && (0L != totalCount)) {
            result.buildPage(totalCount, roleQueryVO);

            List<AuthRoleDO> roles = roleMapper.queryRoleQueryList(roleQueryVO);
            result.setData(roles);
        } else {
            List<AuthRoleDO> roles = new ArrayList<>();
            result.setData(roles);
        }

        return result;
    }
}