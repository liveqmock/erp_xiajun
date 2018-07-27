package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallReturnOrderVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/08
 */
public interface IMallReturnOrderService {

    void add(MallReturnOrderVO erpReturnOrder);

    void update(MallReturnOrderVO erpReturnOrder);

    List<MallReturnOrderDO> selectByCondition(String orderNo, String startGmtCreate, String endGmtCreate);

    MallReturnOrderDO getByMallReturnOrderNo(String mallReturnOrderNo);
}
