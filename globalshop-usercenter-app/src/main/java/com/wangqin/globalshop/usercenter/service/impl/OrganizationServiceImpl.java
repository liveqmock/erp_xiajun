package com.wangqin.globalshop.usercenter.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthOrganizationDOMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dataVo.OrganizationQueryVO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.result.Tree;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangqin.globalshop.usercenter.service.IOrganizationService;

/**
 *
 * AuthOrganizationDO 表数据服务层接口实现类
 *
 */
@Service
@Authenticated
public class OrganizationServiceImpl implements IOrganizationService {

	@Autowired
	private AuthOrganizationDOMapperExt organizationMapper;
	
	@Override
	public AuthOrganizationDO selectByPrimaryKey(Long id) {
		return organizationMapper.selectByPrimaryKey(id);
	}
	
	@Override
	@Transactional(rollbackFor = ErpCommonException.class)
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
		// EntityWrapper<AuthOrganizationDO> wrapper = new
		// EntityWrapper<AuthOrganizationDO>();
		// wrapper.orderBy("seq");
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
		return organizationMapper.insert(organization);
	}

	@Override
	public AuthOrganizationDO selectById(Long id) {
		return organizationMapper.selectByPrimaryKey(id);
	}

	@Override
	public JsonPageResult<List<AuthOrganizationDO>> queryOrganizationList(String companyNo) {
		JsonPageResult<List<AuthOrganizationDO>> result = new JsonPageResult<>();

		List<AuthOrganizationDO> list = organizationMapper.queryOrganizationQueryList(companyNo);
		result.setData(list);

		return result.buildIsSuccess(true);
	}

	@Override
	public int insertByOrganizationVo(OrganizationQueryVO organizationQueryVO) {
		// TODO Auto-generated method stub
		organizationQueryVO.setModifier(AppUtil.getLoginUserId());
		organizationQueryVO.setCreator(AppUtil.getLoginUserId());
		organizationQueryVO.setIsDel(false);
		organizationQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		return organizationMapper.insertByOrganizationVo(organizationQueryVO);
	}

	@Override
	public int updateByOrganizationVo(OrganizationQueryVO organizationQueryVO) {
		// TODO Auto-generated method stub
		organizationQueryVO.setModifier(AppUtil.getLoginUserId());
		organizationQueryVO.setCreator(AppUtil.getLoginUserId());
		organizationQueryVO.setIsDel(false);
		organizationQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		return organizationMapper.updateByOrganizationVo(organizationQueryVO);
	}
}
