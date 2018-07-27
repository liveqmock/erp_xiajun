package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingOrderDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderVO;

import java.util.List;

public interface ShippingOrderDOMapperExt extends ShippingOrderDOMapper {

	public List<ShippingOrderDO> queryShippingOrders(ShippingOrderVO shippingOrderVO);
}
