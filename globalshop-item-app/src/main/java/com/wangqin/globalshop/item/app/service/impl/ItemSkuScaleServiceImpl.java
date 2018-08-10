package com.wangqin.globalshop.item.app.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CountryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.app.service.ICountryService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;

/**
 * @author XiaJun
 */

@Service
public class ItemSkuScaleServiceImpl implements IItemSkuScaleService {

    @Autowired
    private ItemSkuScaleMapperExt itemSkuScaleMapperExt;

    //查询sku对应的所有规格以及规格的值
    @Override
    public List<ItemSkuScaleDO> selectScaleNameValueBySkuCode(String skuCode) {
        return itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(skuCode);
    }

    @Override
    public void insertBatch(List<ItemSkuScaleDO> scaleList) {
        itemSkuScaleMapperExt.insertBatch(scaleList);
    }

    //更新skuscale
    @Override
    public void updateSkuScaleBySkuCodeAndScaleName(String skuCode,
                                                    String scaleName, String scaleValue) {
        itemSkuScaleMapperExt.updateSkuScaleBySkuCodeAndScaleName(skuCode, scaleName, scaleValue);
    }

    //删除item_sku_scale
    @Override
    public void deleteItemSkuScaleBySkuCodeAndScaleName(String skuCode, String scaleName) {
        itemSkuScaleMapperExt.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, scaleName);
    }
    
    @Override
    public void insertSelective(ItemSkuScaleDO scale) {
    	itemSkuScaleMapperExt.insertSelective(scale);
    }
}
