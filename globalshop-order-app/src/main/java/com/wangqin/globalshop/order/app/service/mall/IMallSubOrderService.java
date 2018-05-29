package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IMallSubOrderService {

    List<MallSubOrderDO> selectList(MallSubOrderDO tjErpOrder);


    List<MallSubOrderDO> selectByOrderNo(String orderNo);
}
