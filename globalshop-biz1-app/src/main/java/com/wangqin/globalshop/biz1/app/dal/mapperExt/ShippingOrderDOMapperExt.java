package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingOrderDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderVO;

import java.util.List;

public interface ShippingOrderDOMapperExt extends ShippingOrderDOMapper {

	public List<ShippingOrderDO> queryShippingOrders(ShippingOrderVO shippingOrderVO);
	
	//首页数据看板：今日已发货包裹数
	Integer sumTodySentNum(String companyNo);
	
	//首页数据看板：一周内已发货包裹数
	Integer sumWeekSentNum(String companyNo);
	
}
