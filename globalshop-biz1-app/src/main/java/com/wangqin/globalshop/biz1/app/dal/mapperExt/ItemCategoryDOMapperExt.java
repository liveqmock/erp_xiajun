package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryDOMapper;

public interface ItemCategoryDOMapperExt extends ItemCategoryDOMapper{

    ItemCategoryDO selectByCode(String categoryCode);

}
