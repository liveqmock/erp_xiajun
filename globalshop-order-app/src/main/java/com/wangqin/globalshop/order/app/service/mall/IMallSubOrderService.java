package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.biz1.app.vo.MallSubOrderExcelVO;
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
    //导出子订单1
    List<MallSubOrderExcelVO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO);
    //导出子订单2
    List<MallSubOrderExcelVO> queryErpOrderForExcelByIdList(List<Long> idList);


    List<MallSubOrderDO> list();
    /**
     * 根据订单号找出已发货的单数
     * @return
     */
    int selectCountWithStateAndOrderNo(String orderNo, Integer orderSatutsSent);
    
    void updateByIsDel(MallSubOrderVO mallSubOrderVO); 
    
    List<MallSubOrderVO> selectByOrderNoVo(String ordderNo);
    
    void deleteByHardSub(Long id);

    List<MallSubOrderDO> queryExpiredSubOrders(Integer status, Long timeOut);

    void updateSubOrderStatus(Integer orderSatutsInit, Integer orderSatutsClose, Long timeOut);

    MallSubOrderDO selectBySubOrderNo(String subOrderNo);

    void returns(String subOrderNo);

    /**
     * 通过包裹号获取 MallSubOrder，主要是配合发货单管理获取子订单信息
     *
     * @param shippingNo
     * @return
     */
    MallSubOrderDO getByShippingNo(String shippingNo);

    /**
     * 根据主订单号删除子订单
     *
     * @param orderNo 主订单号
     */
    void deleteByOrderNo(String orderNo);

    List<MallSubOrderDO> queryByMallSubOrderNos(List<String> noList);
}

