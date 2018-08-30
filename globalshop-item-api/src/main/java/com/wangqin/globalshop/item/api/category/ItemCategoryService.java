package com.wangqin.globalshop.item.api.category;




import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.bean.dto.ItemCategoryDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.common.utils.Tree;


public interface ItemCategoryService {

	 @RequestMapping(value = "/category/queryByCategoryCode", method = RequestMethod.POST)
	   ItemCategoryDO queryByCategoryCode(@RequestParam("categoryCode")String categoryCode);
		/**
		 * 获取全部类目
		 * @return
		 */
	    @RequestMapping(value = "/category/selectAll", method = RequestMethod.POST)
	   List<ItemCategoryDO> selectAll();


	    @RequestMapping(value = "/category/selectAllMenu", method = RequestMethod.POST)
	   List<Tree> selectAllMenu();

	    @RequestMapping(value = "/category/insert", method = RequestMethod.POST)
	   void insert(@RequestBody ItemCategoryDO category);

	    @RequestMapping(value = "/category/findCategory", method = RequestMethod.POST)
	  ItemCategoryDO findCategory(@RequestParam("id")Long id);

	    @RequestMapping(value = "/category/update", method = RequestMethod.POST)
	   void update(@RequestBody ItemCategoryDO category);

		/**
		 * 获取树形结构类目
		 * @return
		 */
	    @RequestMapping(value = "/category/tree", method = RequestMethod.POST)
	   List<ItemCategoryDTO> tree();

	    @RequestMapping(value = "/category/deleteById", method = RequestMethod.POST)
	   void deleteById(@RequestBody ItemCategoryDO category);

	    @RequestMapping(value = "/category/selectByPrimaryKey", method = RequestMethod.POST)
	   ItemCategoryDO selectByPrimaryKey(@RequestParam("id")Long id);

	    @RequestMapping(value = "/category/selectAllDTO", method = RequestMethod.POST)
	   List<ItemCategoryDTO> selectAllDTO();

	   //插入类目
	   @RequestMapping(value = "/category/insertCategorySelective", method = RequestMethod.POST)
	   void insertCategorySelective(@RequestBody ItemCategoryDO category);

	   //不能删除有商品的类目
	   @RequestMapping(value = "/category/countRelativeItem", method = RequestMethod.POST)
	   int countRelativeItem(@RequestParam("categoryCode")String categoryCode);

	   //不能删除有子类目的类目
	   @RequestMapping(value = "/category/queryChildCategoryCountByCategoryCode", method = RequestMethod.POST)
	   int queryChildCategoryCountByCategoryCode(@RequestParam("categoryCode")String categoryCode);

	   //根据id删除类目
	   @RequestMapping(value = "/category/deleteItemCategoryById", method = RequestMethod.POST)
	   void deleteItemCategoryById(@RequestParam("id")Long id);

	    @RequestMapping(value = "/category/selectByName", method = RequestMethod.POST)
	   List<ItemCategoryDO> selectByName(@RequestParam("category1")String category1);

	    @RequestMapping(value = "/category/selectByParentAndName", method = RequestMethod.POST)
	   List<ItemCategoryDO> selectByParentAndName(@RequestBody List<ItemCategoryDO> categoryList1, @RequestParam("category2") String category2);

	    @RequestMapping(value = "/category/queryItemCategoryByPcode", method = RequestMethod.POST)
	   List<ItemCategoryDO> queryItemCategoryByPcode(@RequestParam("pCode")String pCode);

	   //通过code查询category
	   @RequestMapping(value = "/category/queryCategoryByCategoryCode", method = RequestMethod.POST)
	   ItemCategoryDO queryCategoryByCategoryCode(@RequestParam("categoryCode")String categoryCode);
}
