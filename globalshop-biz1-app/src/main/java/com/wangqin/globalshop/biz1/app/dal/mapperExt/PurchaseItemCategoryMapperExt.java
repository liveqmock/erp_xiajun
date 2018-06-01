package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryDOMapper;

public interface PurchaseItemCategoryMapperExt extends ItemCategoryDOMapper {

    List<ItemCategoryDO> selectByTop();

    List<ItemCategoryDO> selectListByPidList(List<ItemCategoryDO> categoryList);

    List<ItemCategoryDO> selectByPid(String pCode);

}
