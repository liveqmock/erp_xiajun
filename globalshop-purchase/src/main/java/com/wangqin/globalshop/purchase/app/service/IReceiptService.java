package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerReceiptDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IReceiptService {

    List<BuyerReceiptDO> list(BuyerReceiptDO receipt);
}
