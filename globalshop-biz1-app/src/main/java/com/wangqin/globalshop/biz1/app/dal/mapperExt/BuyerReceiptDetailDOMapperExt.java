package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDetailDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerReceiptDetailDOMapper;

import java.util.List;

public interface BuyerReceiptDetailDOMapperExt extends BuyerReceiptDetailDOMapper {
    List<BuyerReceiptDetailDO> list(BuyerReceiptDetailDO receipt);
}