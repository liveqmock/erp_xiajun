package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemSkuScaleDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;




/**
 * 
 * @author xiajun
 *
 */
public interface ItemSkuScaleMapperExt extends ItemSkuScaleDOMapper {

	//查询sku对应的所有规格以及规格的值
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode);

    void insertBatch(@Param("scaleList") List<ItemSkuScaleDO> scaleList);
    
    //更新skuscale
    void updateSkuScaleBySkuCodeAndScaleName(@Param("skuCode")String skuCode,
    		@Param("scaleName")String scaleName,@Param("scaleValue")String scaleValue);
}
