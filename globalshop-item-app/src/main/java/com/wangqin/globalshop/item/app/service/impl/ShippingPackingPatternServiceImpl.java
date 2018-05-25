package com.wangqin.globalshop.item.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wangqin.globalshop.item.app.service.IShippingPackingPatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemBrandDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IItemBrandService;





@Service
public class ShippingPackingPatternServiceImpl  implements IShippingPackingPatternService {

	@Autowired
	private ItemBrandDOMapperExt itemBrandDOMapperExt;
	
	@Override
	public void add(ItemBrandDO itemBrand) {
		
		//itemBrandDOMapperExt.insert(itemBrand);
	}
	
	
	

	@Override
	public void addBrand(ItemBrandDO brand) {
		//String userCreate = ShiroUtil.getShiroUser().getLoginName();
		String userCreate = "admin";
		brand.setCreator(userCreate);
		brand.setGmtCreate(new Date());
		brand.setGmtModify(new Date());
		brand.setModifier(userCreate);
		itemBrandDOMapperExt.insert(brand);
	}

	@Override
	public void updateBrand(ItemBrandDO brand) {
		//String userModify = ShiroUtil.getShiroUser().getLoginName();
		String userModify = "admin";
		brand.setModifier(userModify);
		brand.setGmtModify(new Date());
		itemBrandDOMapperExt.updateByPrimaryKeySelective(brand);
	}

	@Override
	public JsonPageResult<List<ItemBrandDO>> queryAllBrand() {
		JsonPageResult<List<ItemBrandDO>> brandResult = new JsonPageResult<>();
		List<ItemBrandDO> brandList = itemBrandDOMapperExt.queryAllItemBrand();
		brandResult.setData(brandList);
		return brandResult;
	}

	@Override
	public JsonPageResult<List<ItemBrandDO>> queryBrands(ItemBrandQueryVO brandQueryVO) {
		JsonPageResult<List<ItemBrandDO>> brandResult = new JsonPageResult<>();
		// 查询总条数
		Integer totalCount = itemBrandDOMapperExt.queryItemBrandCount(brandQueryVO);
		// 2、查询分页记录
		if (totalCount != null && totalCount != 0L) {
			brandResult.buildPage(totalCount.intValue(), brandQueryVO);
			List<ItemBrandDO> queryBrands = itemBrandDOMapperExt.queryAllItemBrand();
			brandResult.setData(queryBrands);
		} else {
			List<ItemBrandDO> queryBrands = new ArrayList<>();
			brandResult.setData(queryBrands);
		}
		return brandResult;
	}
	
	/**
	 * 根据品牌英文名查找品牌，防止重复
	 * @author XiaJun
	 * @param name
	 * @return
	 */
	@Override
	public ItemBrandDO selectByName(String name) {
		return itemBrandDOMapperExt.selectByName(name);
	}
	
	@Override
	public ItemBrandDO selectByPrimaryKey(Long id) {
		return itemBrandDOMapperExt.selectByPrimaryKey(id);
	}
	
	@Override
	public  void deleteByPrimaryKey(Long id) {
		itemBrandDOMapperExt.deleteByPrimaryKey(id);
	}
	
	

}
