package com.wangqin.globalshop.item.api.itembrand;


import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 
 */


public interface ItemBrandApi {


    //void add(ItemBrandDO itemBrand);

	@RequestMapping(value = "/addBrand", method = RequestMethod.POST)
    void addBrand(@RequestBody ItemBrandDO brand);

	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	void updateBrand(@RequestBody ItemBrandDO brand);

	//分页查询
	@RequestMapping(value = "/queryBrands", method = RequestMethod.POST)
	JsonPageResult<List<ItemBrandDO>> queryBrands(@RequestBody ItemBrandQueryVO brandQueryVO);

	//总查询
	@RequestMapping(value = "/queryAllBrand", method = RequestMethod.POST)
	JsonPageResult<List<ItemBrandDO>> queryAllBrand();

	/**
	 * 根据品牌英文名查找品牌，防止重复
	 * @author XiaJun
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/selectBrandNoByName", method = RequestMethod.POST)
	String selectBrandNoByName(@RequestParam("name") String name);

	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	ItemBrandDO selectByPrimaryKey(@RequestParam("id") Long id);

	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.POST)
	void deleteByPrimaryKey(@RequestParam("id") Long id);

	//插入单个品牌
	@RequestMapping(value = "/insertBrandSelective", method = RequestMethod.POST)
    int insertBrandSelective(@RequestBody ItemBrandDO brand);

	@RequestMapping(value = "/deleteItemBrandById", method = RequestMethod.POST)
    void deleteItemBrandById(@RequestParam("id") Long id);

	@RequestMapping(value = "/queryByEnNameAndCnName", method = RequestMethod.POST)
	List<ItemBrandDO> queryByEnNameAndCnName(@RequestParam("enName") String enName,@RequestParam("cnName") String cnName);
	
	//修改之前查询是不是已经有该英文名的品牌
	@RequestMapping(value = "/queryIdListByBrandName", method = RequestMethod.POST)
    List<Long> queryIdListByBrandName(@RequestParam("name") String name);

	@RequestMapping(value = "/queryByEnName", method = RequestMethod.POST)
    List<ItemBrandDO> queryByEnName(@RequestParam("brandEnName") String brandEnName);
}
