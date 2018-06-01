package com.wangqin.globalshop.item.app.service.impl;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerMapperExt;
import com.wangqin.globalshop.item.app.service.IBuyerService;

@Service
public class BuyerServiceImplement implements IBuyerService{

	@Autowired
	private BuyerMapperExt buyerMapperExt;
	
	@Override
	public List<BuyerDO> queryAllBuyers() {
		return buyerMapperExt.queryAllBuyers();
	}
}
