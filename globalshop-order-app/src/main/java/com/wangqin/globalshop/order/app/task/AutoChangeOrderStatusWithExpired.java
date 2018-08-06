package com.wangqin.globalshop.order.app.task;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.order.app.kuaidi_bean.CommonShippingTrack;
import com.wangqin.globalshop.order.app.kuaidi_bean.CommonShippingTrackState;
import com.wangqin.globalshop.order.app.kuaidi_bean.Kuaidi100ShippingTrackResult;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.mall.IOrderMallCommisionApplyService;
import com.wangqin.globalshop.order.app.service.shipping.kuaidi100.IKuaidi100Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/08/02
 */
@Component
public class AutoChangeOrderStatusWithExpired {
    private static Logger log = LoggerFactory.getLogger("AutoChangeOrderStatusWithExpired");
    @Autowired
    private IMallSubOrderService mallSubOrderService;
    @Autowired
    private IMallOrderService mallOrderService;
    @Autowired
    private IKuaidi100Service kuaidi100Service;
    @Autowired
    private IOrderMallCommisionApplyService applyService;


    @Scheduled(cron = "0 0/1 * * * ?")
    public void AutoChangeOrderStatusWithExpired() {
        log.info("轮询更新订单物流状态");
        /*查询创建时间超过一天的订单记录*/
        Long timeOut = (long) (60 * 24);
        List<MallSubOrderDO> mallSubOrderDOList = mallSubOrderService.queryExpiredSubOrders(OrderStatus.SENT.getCode(), timeOut);

        for (MallSubOrderDO subOrder : mallSubOrderDOList) {
            /*通过子订单查询通用的物流轨迹*/
            String shippingNo = subOrder.getShippingNo();
            try {

                Kuaidi100ShippingTrackResult kuaidi100ShippingTrackResult = kuaidi100Service.queryShippingTrack(shippingNo);
                CommonShippingTrack commonShippingTrack = kuaidi100ShippingTrackResult.toCommonShippingTrack();
                String state = commonShippingTrack.getState();
            /*如果物流轨迹的状态为已签收  则更新子订单的状态*/
                if (Integer.valueOf(state) == CommonShippingTrackState.HAVE_RECEIVE.getState()) {
                    log.info("开始更新主订单的状态" + subOrder.getOrderNo());
                    updateOrderStatus(subOrder);

                }
            } catch (ErpCommonException e) {
                log.error(e.getErrorMsg());
            }
            //todo 更新shippingOrder状态

        }


    }

    @Transactional(rollbackFor = ErpCommonException.class)
    void updateOrderStatus(MallSubOrderDO subOrder) {
        subOrder.setStatus(OrderStatus.COMFIRM.getCode());
        mallSubOrderService.update(subOrder);
        /*找出对应的主订单*/
        String orderNo = subOrder.getOrderNo();
        MallOrderDO mallOrderDO = mallOrderService.selectByOrderNo(orderNo);
        //更新分佣申请表的状态,mall_commmision_apply.status,@author:xiajun
        if (IsEmptyUtil.isStringNotEmpty(subOrder.getSubOrderNo())) {
            applyService.updateStatusBySubOrderNo(subOrder.getSubOrderNo());
        }

        /*如果主订单的状态为全部发货*/
        if (OrderStatus.SENT.getCode() == mallOrderDO.getStatus()) {
            List<MallSubOrderDO> list = mallSubOrderService.selectByOrderNo(orderNo);
            list.remove(subOrder);
            if (needChangeMallOrderStatus(list)) {
                mallOrderDO.setStatus(OrderStatus.COMFIRM.getCode());
                mallOrderService.updateById(mallOrderDO);
            }
        }
    }

    /**
     * 判断主订单是否需要更新状态
     *
     * @param list 除了当前子订单之外的所有该主订单下面的子订单
     * @return true 需要更新主订单状态 false不需要更新主订单的状态
     */
    private boolean needChangeMallOrderStatus(List<MallSubOrderDO> list) {
        for (MallSubOrderDO mallSubOrderDO : list) {
            if (OrderStatus.SENT.getCode() == mallSubOrderDO.getStatus()) {
                return false;
            }
        }
        return true;
    }

}
