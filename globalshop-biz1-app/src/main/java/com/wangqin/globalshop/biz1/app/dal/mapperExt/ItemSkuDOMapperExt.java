package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;

public interface ItemSkuDOMapperExt extends ItemSkuDOMapper {

    List<ItemSkuDO> selectByItemCode(String itemCode);

    ItemSkuDO selectByUpc(String upc);

}
