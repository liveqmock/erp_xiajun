package com.wangqin.globalshop.order.app.task;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@Component
public class AutoCloseExpiredOrderTask {
    private static Logger log = LoggerFactory.getLogger("AutoCloseExpiredOrderTask");
    private static final long DEFAULT_TIME_OUT_IN_MINUTE = 10L;
    @Value("#{sys.CLOSE_TASK_TIME_OUT}")
    private Long timeOut;
    @Autowired
    IItemService itemService;


    @Autowired
    private IMallOrderService mallOrderService;
    @Autowired
    private IMallSubOrderService mallSubOrderService;
    @Autowired
    private InventoryService inventoryService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void autoCloseExpiredOrder() {
        log.info("轮询关闭订单开始");
        /**十分钟*/
        timeOut = timeOut == null ? DEFAULT_TIME_OUT_IN_MINUTE : timeOut;
        List<MallOrderDO> mallOrderlist = mallOrderService.queryExpiredSubOrders(OrderStatus.INIT.getCode(), timeOut);
        for (MallOrderDO mallOrder : mallOrderlist) {
            try {
                mallOrderService.closeOrder(mallOrder);
            } catch (ErpCommonException e) {
                e.printStackTrace();
            }

        }
        log.info("轮询关闭订单结束");

    }


}
