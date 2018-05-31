package com.wangqin.globalshop.order.app.service.category;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;

public interface OrderICategoryService {
	ItemCategoryDO selectByCategoryCode(String categoryCode);

}
