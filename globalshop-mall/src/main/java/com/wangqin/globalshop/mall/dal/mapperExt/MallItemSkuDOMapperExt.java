package com.wangqin.globalshop.mall.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuDOMapper;

public interface MallItemSkuDOMapperExt extends ItemSkuDOMapper {

    ItemSkuDO selectByCode(String skuCode);

}
