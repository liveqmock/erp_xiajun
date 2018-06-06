package com.wangqin.globalshop.item.app.service;


import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;



/**
 * 
 */


public interface IItemBrandService {


    //void add(ItemBrandDO itemBrand);
      
    void addBrand(ItemBrandDO brand);

	void updateBrand(ItemBrandDO brand);

	//分页查询
	JsonPageResult<List<ItemBrandDO>> queryBrands(ItemBrandQueryVO brandQueryVO);

	//总查询
	JsonPageResult<List<ItemBrandDO>> queryAllBrand();
	
	/**
	 * 根据品牌英文名查找品牌，防止重复
	 * @author XiaJun
	 * @param name
	 * @return
	 */
	String selectBrandNoByName(String name);
	
	ItemBrandDO selectByPrimaryKey(Long id);
	
	void deleteByPrimaryKey(Long id);
    
	//插入单个品牌
    int insertBrandSelective(ItemBrandDO brand);
    
  
}
