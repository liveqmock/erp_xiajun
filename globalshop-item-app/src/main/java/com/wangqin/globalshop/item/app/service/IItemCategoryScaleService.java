package com.wangqin.globalshop.item.app.service;




import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryScaleDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

/**
 *
 * @author Xiajun
 *
 */

public interface IItemCategoryScaleService {

	List<ItemCategoryScaleDO> selectCategoryScaleByCategoryCode(String categoryCode);
}
