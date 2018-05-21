package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;

public interface BuyerReceiptDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerReceiptDO record);

    int insertSelective(BuyerReceiptDO record);

    BuyerReceiptDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerReceiptDO record);

    int updateByPrimaryKey(BuyerReceiptDO record);
}