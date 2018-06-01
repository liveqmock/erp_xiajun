package com.wangqin.globalshop.mall.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxPayBillDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallWxPayBillDOMapper;

public interface MallWxPayBillDOMapperExt extends MallWxPayBillDOMapper {

    MallWxPayBillDO selectByOutTradeNo(String outTradeNo);

}
