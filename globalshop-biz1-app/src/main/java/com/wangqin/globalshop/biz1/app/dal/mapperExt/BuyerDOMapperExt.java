package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerDOMapper;

public interface BuyerDOMapperExt extends BuyerDOMapper{

    BuyerDO selectByOpenId(String openId);

}
