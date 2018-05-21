package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDetailDO;

public interface BuyerReceiptDetailDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerReceiptDetailDO record);

    int insertSelective(BuyerReceiptDetailDO record);

    BuyerReceiptDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerReceiptDetailDO record);

    int updateByPrimaryKey(BuyerReceiptDetailDO record);
}