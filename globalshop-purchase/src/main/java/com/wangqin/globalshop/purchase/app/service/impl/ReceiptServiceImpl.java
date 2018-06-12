package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerReceiptDOMapperExt;
import com.wangqin.globalshop.purchase.app.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class ReceiptServiceImpl implements IReceiptService {
    @Autowired
    private BuyerReceiptDOMapperExt mapper;
    @Override
    public List<BuyerReceiptDO> list(BuyerReceiptDO receipt) {
        receipt.init();
        return mapper.list(receipt);
    }
}
