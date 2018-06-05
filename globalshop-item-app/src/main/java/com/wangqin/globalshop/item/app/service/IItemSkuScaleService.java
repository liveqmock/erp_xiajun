package com.wangqin.globalshop.item.app.service;




import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

/**
 * 
 * @author Xiajun
 *
 */

public interface IItemSkuScaleService {

	//查询sku对应的所有规格以及规格的值
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode);
}
