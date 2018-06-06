package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dto.ItemCategoryDTO;
import com.wangqin.globalshop.common.utils.Tree;

import java.util.List;


public interface IItemCategoryService {

	
	   ItemCategoryDO queryByCategoryCode(String categoryCode);
		/**
		 * 获取全部类目
		 * @return
		 */
	   List<ItemCategoryDO> selectAll();

	   /**
	    * 获取有效的类目
	    * @return
	    */
	   //List<Tree> selectAllValid();
	   
	   List<Tree> selectAllMenu();
	   
	   void insert(ItemCategoryDO category);
	   
	  ItemCategoryDO findCategory(Long id);
	   
	   void update(ItemCategoryDO category);
	   
		/**
		 * 获取树形结构类目
		 * @return
		 */
	   List<ItemCategoryDTO> tree();
	   
	   void deleteById(ItemCategoryDO category);
	   
	   ItemCategoryDO selectByPrimaryKey(Long id);
	   
	   List<ItemCategoryDTO> selectAllDTO();

	   //插入类目
	   void insertCategorySelective(ItemCategoryDO category);
	   

}
