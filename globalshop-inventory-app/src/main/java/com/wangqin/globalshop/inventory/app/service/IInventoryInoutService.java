package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryInoutService {
    Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO);

    List<InventoryInoutDO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO);
    
    ItemSkuDO selectItemBySkuCode(String skuCode);
    
    List<InventoryQueryVO> queryInventoryInoutsVo(InventoryQueryVO inventoryQueryVO);
}
