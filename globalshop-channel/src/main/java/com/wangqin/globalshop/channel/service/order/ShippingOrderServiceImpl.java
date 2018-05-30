package com.wangqin.globalshop.channel.service.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.channel.dal.mapperExt.ShippingOrderDOMapperExt;

/**
 *
 * ShippingOrder 表数据服务层接口实现类
 *
 */
@Service("shippingOrderService")
public class ShippingOrderServiceImpl  implements IShippingOrderService {

	@Resource ShippingOrderDOMapperExt shippingOrderDOMapperExt;

	public ShippingOrderDOMapperExt getShippingOrderDOMapperExt() {
		return shippingOrderDOMapperExt;
	}


	public int deleteByPrimaryKey(Long id){
		return shippingOrderDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(ShippingOrderDO record){
		return shippingOrderDOMapperExt.insert(record);
	}

	public int insertSelective(ShippingOrderDO record){
		return shippingOrderDOMapperExt.insertSelective(record);
	}

	public ShippingOrderDO selectByPrimaryKey(Long id){
		return shippingOrderDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(ShippingOrderDO record){
		return shippingOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(ShippingOrderDO record){
		return shippingOrderDOMapperExt.updateByPrimaryKey(record);
	}


}
