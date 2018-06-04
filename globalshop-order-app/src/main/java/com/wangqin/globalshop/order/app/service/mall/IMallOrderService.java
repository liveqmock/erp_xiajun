package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IMallOrderService  {
    MallOrderDO selectById(Long id);

    void updateById(MallOrderDO outerOrder);

    MallOrderDO selectByOrderNo(String orderNo);

    List<MallOrderDO> queryByStatus(byte b);

    void addOuterOrder(MallOrderVO outerOrder);

    void review(String id);

    List<MallOrderDO> queryOuterOrderList(MallOrderVO outerOrderQueryVO);

    void delete(MallOrderDO outerOrder);

//    JsonResult<Object> lockErpOrder(MallSubOrderDO erpOrder) throws InventoryException;

    List<MallOrderDO> queryOuterOrderForExcel(MallOrderVO outerOrderQueryVO);

    List<MallOrderDO> selectByStatus(byte b);

    void addMallOrderDO(MallOrderDO mallOrderDO);
}
