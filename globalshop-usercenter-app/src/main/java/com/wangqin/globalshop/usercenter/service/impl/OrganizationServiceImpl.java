package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganization;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthOrganizationMapper;
import com.wangqin.globalshop.biz1.app.service.IAuthOrganizationService;
import com.wangqin.globalshop.common.result.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 *
 * AuthOrganization 表数据服务层接口实现类
 *
 */
@Service
public class OrganizationServiceImpl extends SuperServiceImpl<AuthOrganizationMapper, AuthOrganization> implements IAuthOrganizationService {


    @Autowired
    private AuthOrganizationMapper organizationMapper;
    
    @Override
    public List<Tree> selectTree() {
        List<Tree> trees = new ArrayList<Tree>();

        List<AuthOrganization> organizationFather = organizationMapper.selectByPIdNull();

        if (organizationFather != null) {
            for (AuthOrganization organizationOne : organizationFather) {
                Tree treeOne = new Tree();

                treeOne.setId(organizationOne.getId());
                treeOne.setText(organizationOne.getName());
                treeOne.setIconCls(organizationOne.getIcon());

                List<AuthOrganization> organizationSon = organizationMapper.selectAllByPId(organizationOne.getId());

                if (organizationSon != null) {
                    List<Tree> tree = new ArrayList<Tree>();
                    for (AuthOrganization organizationTwo : organizationSon) {
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
    public List<AuthOrganization> selectTreeGrid() {
        EntityWrapper<AuthOrganization> wrapper = new EntityWrapper<AuthOrganization>();
        wrapper.orderBy("seq");
        return organizationMapper.selectList(wrapper);
    }


}