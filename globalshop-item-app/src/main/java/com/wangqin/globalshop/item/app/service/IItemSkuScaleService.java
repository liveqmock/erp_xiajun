package com.wangqin.globalshop.item.app.service;




import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

import java.util.List;

/**
 * 
 * @author Xiajun
 *
 */

public interface IItemSkuScaleService {

	//查询sku对应的所有规格以及规格的值
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode);

    void insertBatch(List<ItemSkuScaleDO> scaleList);
}
