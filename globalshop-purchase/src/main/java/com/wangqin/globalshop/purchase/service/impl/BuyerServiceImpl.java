package com.wangqin.globalshop.purchase.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerVO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerDOMapperExt;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.purchase.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerServiceImpl //extends SuperServiceImpl<MallCustomerDOMapper, MallCustomerDO>
		implements IBuyerService {
	
	@Autowired
	private BuyerDOMapperExt buyerDOMapper;
	
	@Override
	public JsonPageResult<List<BuyerDO>> queryWxPurchaseUser(BuyerVO buyerVO) {
		JsonPageResult<List<BuyerDO>> wxPurchaseUserResult = new JsonPageResult<>();
		//1、查询总的记录数量
		Integer totalCount = buyerDOMapper.queryWxPurchaseUserCount(buyerVO);
		
		//2、查询分页记录
		if(totalCount!=null && totalCount!=0){
			wxPurchaseUserResult.buildPage(totalCount, buyerVO);
			List<BuyerDO> queryWxPurchaseUser = buyerDOMapper.queryWxPurchaseUser(buyerVO);
			wxPurchaseUserResult.setData(queryWxPurchaseUser);
		} else {
			List<BuyerDO> queryWxPurchaseUser = new ArrayList<>();
			wxPurchaseUserResult.setData(queryWxPurchaseUser);
		}
		return wxPurchaseUserResult;
	}	

}
