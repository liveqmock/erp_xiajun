package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.common.exception.InventoryException;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IMallSubOrderService {



    List<MallSubOrderDO> selectByOrderNo(String orderNo);

    List<MallSubOrderDO> selectBatchIds(List<Long> erpOrderIdList);

    void updateBatchById(List<MallSubOrderDO> erpOrderList);

    List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO);

    int selectCount(MallSubOrderDO erpOrderQuery);

    void update(MallSubOrderDO order);

    void delete(MallSubOrderDO erpOrder);

    List<MallSubOrderDO> selectUnClosedByOrderNo(String orderNo);

    List<MallSubOrderDO> queryErpOrders(MallSubOrderVO erpOrderQueryVO);

    MallSubOrderDO selectById(Long id);

    void closeErpOrder(MallSubOrderDO erpOrder) throws InventoryException;

    void splitErpOrder(MallSubOrderDO erpOrder, Integer splitCount) throws InventoryException;

//    JsonResult lockErpOrder(MallSubOrderDO erpOrder) throws InventoryException;

    List<MallSubOrderDO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO);


    List<MallSubOrderDO> list();
    /**
     * 根据订单号找出已发货的单数
     * @return
     */
    int selectCountWithStateAndOrderNo(String orderNo, Integer orderSatutsSent);
    
    void updateByIsDel(MallSubOrderVO mallSubOrderVO); 
    
    List<MallSubOrderVO> selectByOrderNoVo(String ordderNo);
    
    void deleteByHardSub(Long id);

    List<MallSubOrderDO> queryExpiredSubOrders(Integer status);

    void updateSubOrderStatus(Integer orderSatutsInit, Integer orderSatutsClose);

    MallSubOrderDO selectBySubOrderNo(String subOrderNo);
}

