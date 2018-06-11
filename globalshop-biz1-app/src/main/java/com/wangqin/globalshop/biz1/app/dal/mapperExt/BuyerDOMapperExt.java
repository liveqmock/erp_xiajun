package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerDOMapper;

import java.util.List;

public interface BuyerDOMapperExt extends BuyerDOMapper{

    BuyerDO selectByOpenId(String openId);
    int queryWxPurchaseUserCount(BuyerVO buyerVO);
    List<BuyerDO> queryWxPurchaseUser(BuyerVO buyerVO);

}
