package com.wangqin.globalshop.mall.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import java.util.List;

public interface IMallSubOrderService {

    List<MallSubOrderDO> queryOrdersByShareUserId(String shareUserId);
}
