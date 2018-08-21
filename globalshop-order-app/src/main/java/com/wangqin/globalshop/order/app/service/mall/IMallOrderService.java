package com.wangqin.globalshop.order.app.service.mall;

import com.wangqin.globalshop.biz1.app.bean.dataVo.MallOrderItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallOrderQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallOrderVO;
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

    public void dealOrder(JdCommonParam jdCommonParam, GlobalshopOrderVo globalshopOrderVo);
    
    void deleteByIsDel(MallOrderVO mallOrderVO);
    
    void deleteByHard(MallOrderVO mallOrderVO);

    void changeOrderStatus(Integer oldStatus, Integer newStatus, Long timeOut);

    void update(MallOrderVO mallOrderVO);

    List<MallOrderDO> queryExpiredSubOrders(int code, Long timeOut);

    Integer changeStatus(Long id,Integer oldStatus, Integer newStatus);

    /**
     * 根据指定条件分页查询订单列表
     *
     * @param mallOrderQueryVO
     * @param pageQueryParam
     * @return
     */
    List<MallOrderItemVO> listMallOrders(MallOrderQueryVO mallOrderQueryVO, PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询订单数目
     *
     * @param mallOrderQueryVO
     * @return
     */
    int countMallOrders(MallOrderQueryVO mallOrderQueryVO);

	List<MallOrderItemVO> searchPageList(MallOrderQueryVO mallOrderQueryVO, PageQueryParam pageQueryParam);
}
