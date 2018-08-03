package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.MallOrderItemVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallOrderQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallOrderVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuhui
 */
public interface MallOrderMapperExt extends MallOrderDOMapper {

    List<MallOrderDO> queryByErpOrderId(@Param("erpOrderIdList") List<Long> erpOrderIdList);

    //List<MallOrderDO> queryByShippingOrderTime(ShippingOrderQueryVO shippingOrderQueryVO);

    List<MallOrderDO> queryUnStockUpErpOrders(@Param("skuId") Long skuId);

    List<MallOrderDO> queryTransStockUpErpOrders(@Param("skuId") Long skuId);

    void updateUpcForErpOrder(MallOrderDO erpOrder);

    void updateWeightForErpOrder(MallOrderDO erpOrder);

    String selectPositionNoByErpOrderId(@Param("erpOrderId") Long erpOrderId);

    MallOrderDO queryHaihuErpOrders(MallSubOrderDO outerOrderDetail);

    List<MallOrderDO> queryHaihuShippingNO(@Param("targetNo") String targetNo, @Param("skuCode") String skuCode);

    List<MallOrderDO> todaySendOrders(@Param("statusList") List<Integer> statusList);

    MallOrderDO selectByOrderNo(String orderNo);

    MallOrderVO selectByOrderNoVO(String orderNo);

    List<MallOrderDO> queryByStatus(byte status);

    List<MallOrderDO> queryOuterOrders(MallOrderVO outerOrderQueryVO);

    Integer queryPoCount(MallOrderDO mallOrderDO);

    MallOrderDO queryPo(MallOrderDO mallOrderDO);

    List<MallOrderDO> queryPoList(MallOrderDO mallOrderDO);

    List<MallOrderVO> list(MallOrderVO vo);

    void updateByIsDel(MallOrderVO mallOrderVO);

    void updateExpiredTaskStatus(@Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus, @Param("timeOut") Long timeOut);

    List<MallOrderDO> queryExpiredSubOrders(@Param("status") int status, @Param("timeOut") Long timeOut);

    Integer changeStatus(@Param("id") Long id, @Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus);

    /**
     * 根据指定条件分页查询订单列表
     *
     * @param mallOrderQueryVO
     * @param pageQueryParam
     * @return
     */
    List<MallOrderItemVO> listMallOrders(@Param("mallOrderQueryVO") MallOrderQueryVO mallOrderQueryVO,
                                         @Param("pageQueryParam") PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询订单数目
     *
     * @param mallOrderQueryVO
     * @return
     */
    int countMallOrders(@Param("mallOrderQueryVO") MallOrderQueryVO mallOrderQueryVO);
}
