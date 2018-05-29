package com.wangqin.globalshop.order.app.service.mall.impl;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import org.springframework.stereotype.Service;

/**
 * @author biscuit
 * @data 2018/05/28
 */
@Service
public class MallOrderServiceImpl extends SuperServiceImpl<MallOrderDOMapper, MallOrderDO>  implements IMallOrderService {
}
