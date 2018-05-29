package com.wangqin.globalshop.order.app.service.inventory.impl;

import com.wangqin.globalshop.biz1.app.constants.enums.InoutOperatorType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapper.InventoryInoutDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.shiro.ShiroUser;
import com.wangqin.globalshop.common.utils.ShiroUtil;
import com.wangqin.globalshop.order.app.service.inventory.OrderIInventoryService;
import com.wangqin.globalshop.order.app.service.inventory.OrderInventoryBookingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/29
 */
@Service
public class OrderIInventoryServiceImpl implements OrderIInventoryService {
    @Autowired
    private InventoryMapperExt inventoryMapper;
    @Autowired
    private OrderInventoryBookingRecordService inventoryRecordService;
    @Autowired
    private InventoryOnWarehouseMapperExt inventoryOnWarehouseMapper;
    @Autowired
    private InventoryInoutDOMapper inoutDOMapper;

    @Override
    public void sendInventroyOrder(MallSubOrderDO order) throws InventoryException {
        InventoryDO inventory = inventoryMapper.getInventoryBySkuId(order.getItemCode(), order.getSkuCode());

        List<InventoryBookingRecordDO> records = inventoryRecordService.queryByErpOrderId(order.getOrderNo());
        int sumSend = 0;
        for (InventoryBookingRecordDO inventoryRecord : records) {
            InventoryOnWareHouseDO inventoryArea = inventoryOnWarehouseMapper.selectById(inventoryRecord.getInventoryAreaId());
            if (inventoryArea == null) {
                throw new InventoryException(
                        String.format("sendInventroy exception : InventoryRecord[%d]  InventoryAreaId[%d] is null",
                                inventoryRecord.getId(), inventoryRecord.getInventoryAreaId()));
            }
            Long send = inventoryRecord.getBookedQuantity();
            inventoryArea.setLockedInv(inventoryArea.getLockedInv() - send);
            inventoryArea.setInventory(inventoryArea.getInventory() - send);

            if (inventoryArea.getLockedInv() < 0 || inventoryArea.getInventory() < 0) {
                throw new InventoryException(String.format(
                        "sendInventroy exception : InventoryRecord[%d] remainLocked[%d] or remainInventory[%d] error!",
                        inventoryRecord.getId(), inventoryArea.getLockedInv(), inventoryArea.getInventory()));
            }
            sumSend += send;
            inventoryOnWarehouseMapper.updateByPrimaryKey(inventoryArea);

            // 记录出仓日志
            String userCreate = null;
            try {
                ShiroUser su = ShiroUtil.getShiroUser();
                userCreate = su.getLoginName();
            } catch (Exception e) {
                throw new ErpCommonException("没有登入");
            }
            InventoryInoutDO inventoryInout = new InventoryInoutDO();
            inventoryInout.setItemCode(inventoryRecord.getItemCode());
            inventoryInout.setSkuCode(inventoryRecord.getSkuCode());
            inventoryInout.setQuantity(inventoryRecord.getBookedQuantity());
            inventoryInout.setInventoryOnWarehouseNo(inventoryRecord.getInventoryOnWarehouseNo());
            inventoryInout.setOperatorType((byte) InoutOperatorType.SALE_OUT.getCode());
            inventoryInout.setCreator(userCreate);
            inventoryInout.setGmtCreate(new Date());
            inventoryInout.setGmtModify(new Date());
            inoutDOMapper.insert(inventoryInout);
        }
        // 总库存的锁定和库存减掉
        inventory.setLockedInv(inventory.getLockedInv() - sumSend);
        inventory.setInv(inventory.getInv() - sumSend);
        inventory.setGmtModify(new Date());
        inventoryMapper.updateByPrimaryKey(inventory);
        if (sumSend != order.getQuantity()) {
            throw new InventoryException(String.format(
                    "sendInventroy exception : sumSend[%d] not equals orderDetail[id =%d, quantity = %d]  !", sumSend,
                    order.getId(), order.getQuantity()));
        }
    }
}
