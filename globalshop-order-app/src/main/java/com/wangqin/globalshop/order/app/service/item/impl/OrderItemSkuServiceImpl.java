package com.wangqin.globalshop.order.app.service.item.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.order.app.service.item.OrderItemSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author biscuit
 * @data 2018/05/30
 */
@Service
public class OrderItemSkuServiceImpl implements OrderItemSkuService {
    @Autowired
    private ItemSkuMapperExt itemSkuMapperExt;
    @Override
    public ItemSkuDO selectBySkuCode(String skuCode) {
        return itemSkuMapperExt.queryItemBySkuCode(skuCode);
    }
}
