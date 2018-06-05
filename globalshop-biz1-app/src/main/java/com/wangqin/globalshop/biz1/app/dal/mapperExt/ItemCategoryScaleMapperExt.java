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

	List<ItemCategoryScaleDO> selectCategoryScaleByCategoryCode(String categoryCode);
}
