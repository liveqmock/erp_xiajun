package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;
import com.wangqin.globalshop.channelapi.dal.GlobalshopOrderVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/05/28
 */
public interface IMallOrderService  {
    MallOrderDO selectById(Long id);

    void updateById(MallOrderDO outerOrder);

    MallOrderDO selectByOrderNo(String orderNo);
    
    MallOrderVO selectByOrderNoVO(String orderNo);
    
    List<MallOrderDO> queryByStatus(byte b);

    void addOuterOrder(MallOrderVO outerOrder);

    void review(String id);

    List<MallOrderDO> queryOuterOrderList(MallOrderVO outerOrderQueryVO);

    void delete(MallOrderDO outerOrder);

//    JsonResult<Object> lockErpOrder(MallSubOrderDO erpOrder) throws InventoryException;

    List<MallOrderDO> queryOuterOrderForExcel(MallOrderVO outerOrderQueryVO);

    List<MallOrderDO> selectByStatus(byte b);

    void addMallOrderDO(MallOrderDO mallOrderDO);

    List<MallOrderVO> list(MallOrderVO vo);


    public void dealOrder(JdCommonParam jdCommonParam, GlobalshopOrderVo globalshopOrderVo);
    
    void deleteByIsDel(MallOrderVO mallOrderVO);
    
    void deleteByHard(MallOrderVO mallOrderVO);

    void changeOrderStatus(Integer oldStatus, Integer newStatus, Long timeOut);

    void update(MallOrderVO mallOrderVO);

}
