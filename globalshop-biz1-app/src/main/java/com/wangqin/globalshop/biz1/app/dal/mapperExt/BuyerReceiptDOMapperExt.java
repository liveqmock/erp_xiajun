package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.ReceiptQueryVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerReceiptDOMapper;

import java.util.List;

public interface BuyerReceiptDOMapperExt extends BuyerReceiptDOMapper {

    int queryReceiptCount(ReceiptQueryVO receiptQueryVO);
    List<BuyerReceiptDO>  queryReceipt(ReceiptQueryVO receiptQueryVO);

    List<BuyerReceiptDO> list(BuyerReceiptDO receipt);
}