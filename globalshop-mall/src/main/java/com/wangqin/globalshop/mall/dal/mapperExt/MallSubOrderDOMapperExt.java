package com.wangqin.globalshop.mall.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;

public interface MallSubOrderDOMapperExt extends MallSubOrderDOMapper {

    MallSubOrderDO selectByRecommend();

}
