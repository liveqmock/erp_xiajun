package com.wangqin.globalshop.order.app.service.item;




import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author Xiajun
 *
 */

public interface OrderItemSkuScaleService {

	//查询sku对应的所有规格以及规格的值
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode);

}
