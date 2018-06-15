package com.wangqin.globalshop.item.app.service;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;

public interface IBuyerService {

	List<BuyerDO> queryAllBuyers();
}
