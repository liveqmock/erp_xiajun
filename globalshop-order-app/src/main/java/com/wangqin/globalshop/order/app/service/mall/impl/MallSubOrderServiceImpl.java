package com.wangqin.globalshop.order.app.service.mall.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import org.springframework.stereotype.Service;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class MallSubOrderServiceImpl extends SuperServiceImpl<MallSubOrderDOMapper, MallSubOrderDO> implements IMallSubOrderService {
}
