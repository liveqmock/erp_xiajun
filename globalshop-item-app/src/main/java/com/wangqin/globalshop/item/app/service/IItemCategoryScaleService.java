package com.wangqin.globalshop.item.app.service;




import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;

import java.util.List;

/**
 *
 * @author Xiajun
 *
 */

public interface IItemCategoryScaleService {

	List<ItemCategoryScaleDO> selectCategoryScaleByCategoryCode(String categoryCode);
}
