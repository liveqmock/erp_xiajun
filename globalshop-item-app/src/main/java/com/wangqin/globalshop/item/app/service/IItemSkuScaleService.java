package com.wangqin.globalshop.item.app.service;




import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author Xiajun
 *
 */

public interface IItemSkuScaleService {

	//查询sku对应的所有规格以及规格的值
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode);

    void insertBatch(List<ItemSkuScaleDO> scaleList);
    

    //更新skuscale
    void updateSkuScaleBySkuCodeAndScaleName(String skuCode,
    		String scaleName,String scaleValue);
}
