package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IMallSubOrderService {

    List<MallSubOrderDO> selectList(MallSubOrderDO tjErpOrder);


    List<MallSubOrderDO> selectByOrderNo(String orderNo);

    List<MallSubOrderDO> selectBatchIds(List<Long> erpOrderIdList);

    void updateBatchById(List<MallSubOrderDO> erpOrderList);

    List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO);
}
