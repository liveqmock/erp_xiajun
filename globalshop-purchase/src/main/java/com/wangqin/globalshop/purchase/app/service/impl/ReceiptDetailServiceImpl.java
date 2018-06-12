package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDetailDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerReceiptDetailDOMapperExt;
import com.wangqin.globalshop.purchase.app.service.IReceiptDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class ReceiptDetailServiceImpl implements IReceiptDetailService {
    @Autowired
    private BuyerReceiptDetailDOMapperExt mapper;
    @Override
    public List<BuyerReceiptDetailDO> list(BuyerReceiptDetailDO receipt) {
        return mapper.list(receipt);
    }
}
