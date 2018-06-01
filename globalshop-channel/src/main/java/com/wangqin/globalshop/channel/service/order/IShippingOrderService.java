package com.wangqin.globalshop.channel.service.order;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;

/**
 *
 * ShippingOrder 表数据服务层接口
 *
 */
public interface IShippingOrderService  {

	public int deleteByPrimaryKey(Long id);

	public int insert(ShippingOrderDO record);

	public int insertSelective(ShippingOrderDO record);

	public ShippingOrderDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(ShippingOrderDO record);

	public int updateByPrimaryKey(ShippingOrderDO record);


}
