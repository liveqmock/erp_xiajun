package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IMallOrderService  {
    MallOrderDO selectById(Long id);

    void updateById(MallOrderDO outerOrder);
}