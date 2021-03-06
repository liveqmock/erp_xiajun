package com.wangqin.globalshop.mall.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dto.MyOrderDTO;
import com.wangqin.globalshop.mall.service.IMallSubOrderService;





@Service
public class MallModuleSubOrderServiceImpl  implements IMallSubOrderService {

	@Autowired
	private MallSubOrderMapperExt mallSubOrderMapperExt;
	
	@Override
	public List<MyOrderDTO> queryOrderByShareUserId(String shareUserId,int start,int pageSize) {
		return mallSubOrderMapperExt.queryOrderByShareUserId(shareUserId,start,pageSize);
	}
	
	@Override
	public List<MallSubOrderDO> queryOrderDetailByTime(String shareUserId,String shareTime,int start,int pageSize) {
		 return mallSubOrderMapperExt.queryOrderDetailByTime(shareUserId, shareTime, start, pageSize);
	 }


}
