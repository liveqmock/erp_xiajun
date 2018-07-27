package com.wangqin.globalshop.item.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemCategoryDTO;
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
   
   //不能删除有商品的类目
   int countRelativeItem(String categoryCode);
   
   //不能删除有子类目的类目
   int queryChildCategoryCountByCategoryCode(String categoryCode);
   
   //根据id删除类目
   void deleteItemCategoryById(Long id);

   List<ItemCategoryDO> selectByName(String category1);

   List<ItemCategoryDO> selectByParentAndName(List<ItemCategoryDO> categoryList1, String category2);
	

   List<ItemCategoryDO> queryItemCategoryByPcode(String pCode);

   //通过code查询category
   ItemCategoryDO queryCategoryByCategoryCode(String categoryCode);

}
