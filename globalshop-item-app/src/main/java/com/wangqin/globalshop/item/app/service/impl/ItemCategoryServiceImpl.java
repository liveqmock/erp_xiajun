package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemCategoryMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ItemCategoryDTO;
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
	
	//插入类目
	@Override
	public void insertCategorySelective(ItemCategoryDO category) {
    	category.setCreator("admin");
    	category.setModifier("admin");
    	categoryMapper.insertCategorySelective(category);
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
		categoryMapper.insertSelective(category);	
	}

	@Override
	public ItemCategoryDO findCategory(Long id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	

	

	@Override
	public void update(ItemCategoryDO category) {
		// TODO Auto-generated method stub
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public ItemCategoryDO selectByPrimaryKey(Long id) {
		return categoryMapper.selectByPrimaryKey(id);
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
	public List<ItemCategoryDTO> selectAllDTO() {
		 return categoryMapper.selectAllDTO();
	 }
	
	/**
	 * 类目树
	 */
	@Override
	public List<ItemCategoryDTO> tree() {
		List<ItemCategoryDTO> cates = selectAllDTO();
		List<ItemCategoryDTO> cateListlevel1 = new ArrayList<>();
		if(cates!=null&&!cates.isEmpty()){
			Map<String,ItemCategoryDTO> categoryLevel1 = new HashMap<>();
			Map<String,ItemCategoryDTO> categoryLevel2 = new HashMap<>();
			cates.forEach(cate->{
				if(cate.getLevel()==1){
					categoryLevel1.put(cate.getCategoryCode(), cate);
					cateListlevel1.add(cate);
				}else if(cate.getLevel()==2){
					categoryLevel2.put(cate.getCategoryCode(), cate);
				}
			});
			cates.forEach(cate->{
				if(cate.getLevel()==3){
					ItemCategoryDTO category = categoryLevel2.get(cate.getpCode());
					category.getChildren().add(cate);
				} else if(cate.getLevel()==2){
					ItemCategoryDTO category = categoryLevel1.get(cate.getpCode());
					category.getChildren().add(cate);
				}
			});
		}

		return cateListlevel1;
	}

	@Override
	public void deleteById(ItemCategoryDO category) {
		category.setIsDel(1);
		categoryMapper.updateByPrimaryKeySelective(category);
		
	}
}
