package com.wangqin.globalshop.biz1.app.service.order;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingOrderDOMapperExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * ShippingOrder 表数据服务层接口实现类
 *
 */
@Service
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
