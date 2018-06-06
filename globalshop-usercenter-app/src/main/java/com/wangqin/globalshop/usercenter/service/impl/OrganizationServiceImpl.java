package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthOrganizationDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthOrganizationDOMapperExt;
import com.wangqin.globalshop.common.result.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.usercenter.service.IOrganizationService;

/**
 *
 * AuthOrganizationDO 表数据服务层接口实现类
 *
 */
@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private AuthOrganizationDOMapperExt organizationMapper;

    @Override
    public List<Tree> selectTree() {
        List<Tree> trees = new ArrayList<Tree>();

        List<AuthOrganizationDO> organizationFather = organizationMapper.selectByPIdNull();

        if (organizationFather != null) {
            for (AuthOrganizationDO organizationOne : organizationFather) {
                Tree treeOne = new Tree();

                treeOne.setId(organizationOne.getId());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<AuthOrganizationDO> organizationSon = organizationMapper.selectAllByPId(organizationOne.getId());

                if (organizationSon != null) {
                    List<Tree> tree = new ArrayList<Tree>();
                    for (AuthOrganizationDO organizationTwo : organizationSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(organizationTwo.getId());
                        treeTwo.setText(organizationTwo.getName());
                        treeTwo.setIconCls(organizationTwo.getIcon());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
        }
        return trees;
    }

    @Override
    public List<AuthOrganizationDO> selectTreeGrid() {
//        EntityWrapper<AuthOrganizationDO> wrapper = new EntityWrapper<AuthOrganizationDO>();
//        wrapper.orderBy("seq");
        return organizationMapper.selectList();
    }

    @Override
    public int deleteById(Long id) {
        return organizationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateSelectiveById(AuthOrganizationDO organization) {
    	organization.init();
        return organizationMapper.updateByPrimaryKey(organization);
    }

    @Override
    public int insert(AuthOrganizationDO organization) {
    	organization.init();
    	organization.setIsDel(true);;
        return organizationMapper.insert(organization);
    }
    public static void main(String[] args) {
    	AuthOrganizationDO a =new AuthOrganizationDO();
    	a.init();
    	System.out.println(a.getModifier());
    }

    @Override
    public AuthOrganizationDO selectById(Long id) {
        return organizationMapper.selectByPrimaryKey(id);
    }
}
