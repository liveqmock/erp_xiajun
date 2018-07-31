package com.wangqin.globalshop.item.app.service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;

import java.util.List;



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
    
    void deleteItemBrandById(Long id);

	List<ItemBrandDO> queryByEnNameAndCnName(String enName,String cnName);
	
	//修改之前查询是不是已经有该英文名的品牌
    List<Long> queryIdListByBrandName(String name);

    List<ItemBrandDO> queryByEnName(String brandEnName);
}
