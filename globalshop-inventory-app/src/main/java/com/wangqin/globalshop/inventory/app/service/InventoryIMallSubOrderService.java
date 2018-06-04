package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.JsonResult;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface InventoryIMallSubOrderService {



    MallSubOrderDO selectById(Long id);


    JsonResult lockErpOrderBySkuId(String skuCode) throws InventoryException;

    MallSubOrderDO queryBySkuCode(String skuId);
}
