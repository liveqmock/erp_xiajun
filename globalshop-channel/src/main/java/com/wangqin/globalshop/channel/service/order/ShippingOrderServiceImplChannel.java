package com.wangqin.globalshop.channel.service.order;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.IShippingOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingOrderDOMapperExt;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * ShippingOrder 表数据服务层接口实现类
 *
 */
@Service("shippingOrderService")
public class ShippingOrderServiceImplChannel implements ChannelIShippingOrderService {

	@Autowired IShippingOrderMapperExt shippingOrderDOMapperExt;

	public IShippingOrderMapperExt getShippingOrderDOMapperExt() {
		return shippingOrderDOMapperExt;
	}


	@Override
    public int deleteByPrimaryKey(Long id){
		return shippingOrderDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(ShippingOrderDO record){
		return shippingOrderDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(ShippingOrderDO record){
		return shippingOrderDOMapperExt.insertSelective(record);
	}

	@Override
    public ShippingOrderDO selectByPrimaryKey(Long id){
		return shippingOrderDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(ShippingOrderDO record){
		return shippingOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(ShippingOrderDO record){
		return shippingOrderDOMapperExt.updateByPrimaryKey(record);
	}


	@Override
    public List<ShippingOrderDO> queryShippingOrders(ShippingOrderVO ShippingOrderVO){
		return shippingOrderDOMapperExt.queryShippingOrders(ShippingOrderVO);
	}



}
