package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuScaleDOMapper;




/**
 * 
 * @author xiajun
 *
 */
public interface ItemSkuScaleMapperExt extends ItemSkuScaleDOMapper {

	//查询sku对应的所有规格以及规格的值
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode);
}
