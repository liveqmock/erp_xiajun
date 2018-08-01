package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerDOMapper;

import java.util.List;

public interface BuyerDOMapperExt extends BuyerDOMapper{



    List<BuyerDO> list(String loginUserCompanyNo);

    BuyerDO searchBuyer(BuyerDO record);

    Long searchBuyerCount(BuyerDO record);

    List<BuyerDO> searchBuyerList(BuyerDO record);

    int update(BuyerDO buyerDO);

    void deleteByUnionId(String wxUnionId);
}
