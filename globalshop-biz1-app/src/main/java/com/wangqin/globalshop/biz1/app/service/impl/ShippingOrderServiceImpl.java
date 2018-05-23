package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingOrderMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrder;
import com.wangqin.globalshop.biz1.app.service.IShippingOrderService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * ShippingOrder 表数据服务层接口实现类
 *
 */
@Service
public class ShippingOrderServiceImpl extends SuperServiceImpl<ShippingOrderMapper, ShippingOrder> implements IShippingOrderService {


}