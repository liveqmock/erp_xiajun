package com.wangqin.globalshop.mall.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;

public interface MallOrderDOMapperExt extends MallOrderDOMapper {

    MallOrderDO selectByOutOrderId(String outOrderId);

}
