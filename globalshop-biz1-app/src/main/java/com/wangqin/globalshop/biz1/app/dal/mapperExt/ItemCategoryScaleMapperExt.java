package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryScaleDOMapper;

import java.util.List;

/**
 *
 * 
 * @author XiaJun
 */
public interface ItemCategoryScaleMapperExt extends ItemCategoryScaleDOMapper{

	//根据类目的编码查找item_category_scale表的记录
	List<ItemCategoryScaleDO> selectCategoryScaleByCategoryCode(String categoryCode);
}
