package com.wangqin.globalshop.item.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemBrandDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ItemBrandQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.item.app.service.IItemBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;





@Service
public class ItemBrandServiceImpl  implements IItemBrandService {

	@Autowired
	private ItemBrandDOMapperExt itemBrandDOMapperExt;
	

	
	

	@Override
	public void addBrand(ItemBrandDO brand) {
		//String userCreate = ShiroUtil.getShiroUser().getLoginName();
		brand.setGmtCreate(new Date());
		brand.setGmtModify(new Date());
		itemBrandDOMapperExt.insertSelective(brand);
	}

	@Override
	public void updateBrand(ItemBrandDO brand) {
		//String userModify = ShiroUtil.getShiroUser().getLoginName();
		brand.setModifier("admin");
		itemBrandDOMapperExt.updateByPrimaryKeySelective(brand);
	}

	
	@Override
	public JsonPageResult<List<ItemBrandDO>> queryAllBrand() {
		JsonPageResult<List<ItemBrandDO>> brandResult = new JsonPageResult<>();
		List<ItemBrandDO> brandList = itemBrandDOMapperExt.queryAllBrand();
		brandResult.setData(brandList);
		return brandResult;
	}

	//分页查询
	@Override
	public JsonPageResult<List<ItemBrandDO>> queryBrands(ItemBrandQueryVO brandQueryVO) {
		JsonPageResult<List<ItemBrandDO>> brandResult = new JsonPageResult<>();
		// 查询总条数
		Integer totalCount = itemBrandDOMapperExt.queryBrandCount(brandQueryVO);
		// 2、查询分页记录
		if (totalCount != null && totalCount != 0L) {
			brandResult.buildPage(totalCount.intValue(), brandQueryVO);
			List<ItemBrandDO> queryBrands = itemBrandDOMapperExt.queryBrands(brandQueryVO);
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
	public String selectBrandNoByName(String name) {
		return itemBrandDOMapperExt.selectNoByName(name);
	}
	
	@Override
	public ItemBrandDO selectByPrimaryKey(Long id) {
		return itemBrandDOMapperExt.selectByPrimaryKey(id);
	}
	
	@Override
	public  void deleteByPrimaryKey(Long id) {
		itemBrandDOMapperExt.deleteByPrimaryKey(id);
	}

	//插入单个品牌
	@Override
	public int insertBrandSelective(ItemBrandDO brand) {
    	return itemBrandDOMapperExt.insertBrandSelective(brand);
    }
	

	//删除品牌
	@Override
	public void deleteItemBrandById(Long id) {
		itemBrandDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
	public List<ItemBrandDO> queryByEnNameAndCnName(String enName,String cnName) {
		return itemBrandDOMapperExt.queryByEnNameAndCnName(enName,cnName);
	}
	
	//修改之前查询是不是已经有该英文名的品牌
	@Override
	public List<Long> queryIdListByBrandName(String name) {
    	return itemBrandDOMapperExt.queryIdListByBrandName(name);
    }
}
