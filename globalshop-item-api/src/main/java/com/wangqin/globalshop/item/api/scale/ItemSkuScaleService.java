package com.wangqin.globalshop.item.api.scale;





import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;

/**
 * 
 * @author Xiajun
 *
 */

public interface ItemSkuScaleService {

	//查询sku对应的所有规格以及规格的值
    @RequestMapping(value = "/skuscale/selectScaleNameValueBySkuCode", method = RequestMethod.POST)
	List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(@RequestParam("skuCode") String skuCode);

    @RequestMapping(value = "/skuscale/insertBatch", method = RequestMethod.POST)
    void insertBatch(@RequestBody List<ItemSkuScaleDO> scaleList);

    @RequestMapping(value = "/skuscale/insertSelective", method = RequestMethod.POST)
    void insertSelective(@RequestBody ItemSkuScaleDO scaleList);

    //更新skuscale
    @RequestMapping(value = "/skuscale/updateSkuScaleBySkuCodeAndScaleName", method = RequestMethod.POST)
    void updateSkuScaleBySkuCodeAndScaleName(@RequestParam("skuCode") String skuCode,
                                             @RequestParam("scaleName") String scaleName, @RequestParam("scaleValue") String scaleValue);
    
    //删除item_sku_scale
    @RequestMapping(value = "/skuscale/deleteItemSkuScaleBySkuCodeAndScaleName", method = RequestMethod.POST)
    void deleteItemSkuScaleBySkuCodeAndScaleName(@RequestParam("skuCode") String skuCode, @RequestParam("scaleName") String scaleName);
}
