package com.wangqin.globalshop.biz1.app.service.order;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrder;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderMapper;
import com.wangqin.globalshop.biz1.app.service.IMallOrderService;
import org.springframework.stereotype.Service;

/**
 *
 * MallOrder 表数据服务层接口实现类
 *
 */
@Service
public class MallOrderServiceImpl extends SuperServiceImpl<MallOrderMapper, MallOrder> implements IMallOrderService {


}
