package com.wangqin.globalshop.order.app.task;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@Component
public class AutoCloseExpiredOrderTask {

//	protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    IItemService itemService;


    @Autowired
    private IMallOrderService mallOrderService;
    @Autowired
    private IMallSubOrderService mallSubOrderService;
    @Autowired
    private InventoryService inventoryService;

    //先5分钟检查一次，后续缩小间隔到1分钟
    @Scheduled(cron = "0 0/5 * * * ?")
    @Transactional(rollbackFor = ErpCommonException.class)
    public void autoCloseExpiredOrder() {
        mallOrderService.changeOrderStatus(OrderStatus.INIT.getCode(), OrderStatus.CLOSE.getCode());
        List<MallSubOrderDO> mallSubOrderDOList = mallSubOrderService.queryExpiredSubOrders(OrderStatus.INIT.getCode());

        mallSubOrderService.updateSubOrderStatus(OrderStatus.INIT.getCode(), OrderStatus.CLOSE.getCode());
        for (MallSubOrderDO mallSubOrderDO : mallSubOrderDOList) {
            inventoryService.tryRelease(mallSubOrderDO);
        }
    }

}
