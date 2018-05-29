package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface OrderMallCustomerService {

    List<MallCustomerDO> selectByRole(Integer i);
}
