package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CountryDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryScaleDOMapper;

/**
 *
 * 
 * @author XiaJun
 */
public interface ItemCategoryScaleMapperExt extends ItemCategoryScaleDOMapper{

	//根据类目的编码查找item_category_scale表的记录
	List<ItemCategoryScaleDO> selectCategoryScaleByCategoryCode(String categoryCode);
}
