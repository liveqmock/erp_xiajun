package com.wangqin.globalshop.purchase.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerVO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

import java.util.List;

public interface IBuyerService {
	
	JsonPageResult<List<BuyerDO>> queryWxPurchaseUser(BuyerVO buyerVO);
}
