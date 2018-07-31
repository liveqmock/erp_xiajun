package com.wangqin.globalshop.inventory.app.service;

import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryInoutDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/01
 */
public interface IInventoryInoutService {
    Integer queryInventoryInoutCount(InventoryQueryVO inventoryQueryVO);

    List<InventoryInoutDO> queryInventoryInouts(InventoryQueryVO inventoryQueryVO);
    
    List<InventoryQueryVO> queryInventoryInoutsVo(InventoryQueryVO inventoryQueryVO);

    /**
     * 根据查询条件分页查询出入库记录
     *
     * @param inventoryInoutQueryVO
     * @param pageQueryParam
     * @return
     */
    List<InventoryInoutItemVO> listInventoryInout(InventoryInoutQueryVO inventoryInoutQueryVO, PageQueryParam pageQueryParam);

    /**
     * 获得指定条件查询出入库记录数目
     *
     * @param inventoryInoutQueryVO
     * @return
     */
    int countInventoryInout(InventoryInoutQueryVO inventoryInoutQueryVO);
}
