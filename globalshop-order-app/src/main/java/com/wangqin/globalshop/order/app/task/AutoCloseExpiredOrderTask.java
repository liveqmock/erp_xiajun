package com.wangqin.globalshop.order.app.task;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

@Component
public class AutoCloseExpiredOrderTask {
    private static Logger log = LoggerFactory.getLogger("AutoCloseExpiredOrderTask");
    public static final long DEFAULT_TIME_OUT_IN_MINUTE = 10L;
    //	protected Logger logger = LogManager.getLogger(getClass());
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

    //先5分钟检查一次，后续缩小间隔到1分钟
    @Scheduled(cron = "0 0/1 * * * ?")
    @Transactional(rollbackFor = com.wangqin.globalshop.biz1.app.exception.BizCommonException.class)
    public void autoCloseExpiredOrder() {
        log.info("轮询关闭订单开始");
//        List<String> errMsg = new ArrayList();

        /**十分钟*/
        timeOut = timeOut == null ? DEFAULT_TIME_OUT_IN_MINUTE : timeOut;
        List<MallSubOrderDO> mallSubOrderDOList = mallSubOrderService.queryExpiredSubOrders(OrderStatus.INIT.getCode(), timeOut);
        List<MallOrderDO> mallOrderlist = mallOrderService.queryExpiredSubOrders(OrderStatus.INIT.getCode(), timeOut);

        for (MallOrderDO mallOrder : mallOrderlist) {
            Integer lien = mallOrderService.changeStatus(mallOrder.getId(), OrderStatus.INIT.getCode(), OrderStatus.CLOSE.getCode());
            log.info("影响行数" + lien);
//           if (lien != 1){
//               errMsg.add("更新失败:orderNo"+mallOrder.getOrderNo());
//           }
        }
        for (MallSubOrderDO mallSubOrder : mallSubOrderDOList) {
            Integer lien = mallSubOrderService.changeStatus(mallSubOrder.getId(), OrderStatus.INIT.getCode(), OrderStatus.CLOSE.getCode());
            log.info("影响行数" + lien);
//            if (lien == 1){
            inventoryService.tryRelease(mallSubOrder);
//            }else {
//                errMsg.add("更新失败:subOrderNo"+mallSubOrder.getSubOrderNo());
//            }
        }
//        if (errMsg.size() != 0){
//            log.error(errMsg.toString());
//            throw new BizCommonException(errMsg.toString());
//        }
        log.info("轮询关闭订单成功");

//        mallOrderService.changeOrderStatus(OrderStatus.INIT.getCode(), OrderStatus.CLOSE.getCode(), timeOut);
//
//        mallSubOrderService.updateSubOrderStatus(OrderStatus.INIT.getCode(), OrderStatus.CLOSE.getCode(), timeOut);
//        for (MallSubOrderDO mallSubOrderDO : mallSubOrderDOList) {
//            inventoryService.tryRelease(mallSubOrderDO);
//        }
    }

}
