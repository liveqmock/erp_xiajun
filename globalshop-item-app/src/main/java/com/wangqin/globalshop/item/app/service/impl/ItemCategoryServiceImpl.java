package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemCategoryMapperExt;
import com.wangqin.globalshop.common.utils.Tree;
import com.wangqin.globalshop.item.app.service.IItemCategoryService;


@Service
public class ItemCategoryServiceImpl implements IItemCategoryService {

	@Autowired
	private ItemCategoryMapperExt categoryMapper;
	
	@Override
	public ItemCategoryDO queryByCategoryCode(String categoryCode) {
		return categoryMapper.queryByCategoryCode(categoryCode);
	}
	
	@Override
	public List<ItemCategoryDO> selectAll() {
		return categoryMapper.selectAll();
	}
	
	@Override
	 public void deleteByPrimaryKey(Long id) {
		 categoryMapper.deleteByPrimaryKey(id);
	 }

	

	@Override
	public List<Tree> selectAllMenu() {
		List<Tree> trees = new ArrayList<Tree>();
		// 查询所有菜单
		List<ItemCategoryDO> categories = this.selectAll();
		if (categories == null) {
			return trees;
		}
		for (ItemCategoryDO category : categories) {
			Tree tree = new Tree();
			tree.setId(category.getId());
			//tree.setpCode(category.getpCode());
			tree.setText(category.getName());
//			tree.setIconCls(resource.getIcon());
//			tree.setAttributes(resource.getUrl());
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public void insert(ItemCategoryDO category) {
		categoryMapper.insert(category);		
	}

	@Override
	public ItemCategoryDO findCategory(Long id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	

	

	@Override
	public void update(ItemCategoryDO category) {
		// TODO Auto-generated method stub
		categoryMapper.updateByPrimaryKey(category);
	}

	

	

	

	/*
	@Override
	public List<Tree> selectAllValid() {
		List<Tree> trees = new ArrayList<Tree>();
		//EntityWrapper<Category> wrapper = new EntityWrapper<Category>();
		///wrapper.orderBy("seq");
		//wrapper.where("status={0}", "0");
		List<ItemCategoryDO> categories =categoryMapper.selectList(wrapper);
		if (categories == null) {
			return trees;
		}
		for (ItemCategoryDO category : categories) {
			Tree tree = new Tree();
			tree.setId(category.getId());
			//tree.setPid(category.getPid());
			tree.setText(category.getName());
//			tree.setIconCls(resource.getIcon());
//			tree.setAttributes(resource.getUrl());
			trees.add(tree);
		}
		return trees;
	}
*/
	
	@Override
	public List<ItemCategoryDO> tree() {
	/*	List<ItemCategoryDO> cates = selectAll();
		List<ItemCategoryDO> cateListlevel1 = new ArrayList<>();
		if(cates!=null&&!cates.isEmpty()){
			Map<Long,ItemCategoryDO> categoryLevel1 = new HashMap<>();
			Map<Long,ItemCategoryDO> categoryLevel2 = new HashMap<>();
			cates.forEach(cate->{
				if(cate.getLevel()==1){
					categoryLevel1.put(cate.getId(), cate);
					cateListlevel1.add(cate);
				}else if(cate.getLevel()==2){
					categoryLevel2.put(cate.getId(), cate);
				}
			});
			cates.forEach(cate->{
				if(cate.getLevel()==3){
					ItemCategoryDO category = categoryLevel2.get(cate.getPid());
					category.getChildren().add(cate);
				} else if(cate.getLevel()==2){
					ItemCategoryDO category = categoryLevel1.get(cate.getPid());
					category.getChildren().add(cate);
				}
			});
		}
		return cateListlevel1;*/
		return null;
	}
}
