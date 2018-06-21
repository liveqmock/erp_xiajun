package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;

import java.util.List;

public interface ItemSkuDOMapperExt extends ItemSkuDOMapper {

    List<ItemSkuDO> selectByItemCode(String itemCode);

    ItemSkuDO selectByUpc(String upc);


    void inserBatch(List<ItemSkuDO> skuList);
}
