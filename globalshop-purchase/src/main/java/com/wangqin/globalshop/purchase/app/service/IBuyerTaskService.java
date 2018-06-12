package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerTaskService {

    List<BuyerTaskDO> list(BuyerTaskDO buyerTaskDO);
}
