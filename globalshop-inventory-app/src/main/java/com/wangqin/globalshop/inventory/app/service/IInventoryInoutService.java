package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryInoutVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.InventoryQueryVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryInoutService {
    Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO);

    List<InventoryInoutVO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO);
}
