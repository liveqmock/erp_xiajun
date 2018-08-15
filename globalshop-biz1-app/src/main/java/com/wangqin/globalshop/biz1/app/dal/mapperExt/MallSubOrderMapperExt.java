package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dto.MyOrderDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品 数据控制层
 *
 * @author zhulu
 */
public interface MallSubOrderMapperExt extends MallSubOrderDOMapper {
	
	//首页数据看板：今日GMV（已付款订单金额）
	Double sumPaidOrderPriceByDate(@Param("statusList")List statusList,@Param("dayIndex")Integer dayIndex,
			@Param("companyNo")String companyNo);
	
	//首页数据看板：一周GMV（已付款订单数，不含退款订单）
	Double sumWeekOrderPrice (@Param("statusList")List statusList,@Param("companyNo")String companyNo);
	
	//销量的计算，用在商品管理商品列表，计算已付款的订单和通过erp新建的订单
	Integer calItemSalesVolume(@Param("statusList")List statusList,@Param("itemCode")String itemCode,
			@Param("companyNo")String companyNo);
	
    @Override
    int deleteByPrimaryKey(Long id);

    @Override
    int insertSelective(MallSubOrderDO record);

    @Override
    MallSubOrderDO selectByPrimaryKey(Long id);

    List<MallSubOrderDO> queryExpiredSubOrders(@Param("status") Integer status, @Param("timeOut") Long timeOut);

    //关闭超时订单，后面再调一下超时时间参数
    void updateSubOrderStatus(@Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus, @Param("timeOut") Long timeOut);


    List<MallSubOrderDO> selectList(MallSubOrderDO order);


    List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO);


    List<MallSubOrderDO> selectUnClosedByOrderNo(String orderNo);

    Integer queryErpOrdersCount(MallSubOrderVO erpOrderQueryVO);

    List<MallSubOrderDO> queryErpOrders(MallSubOrderVO erpOrderQueryVO);

    List<MallSubOrderDO> selectByOrderNo(String orderNo);

    List<MallSubOrderVO> selectByOrderNoVo(String ordderNo);

    //导出子订单1
    List<MallSubOrderExcelVO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO);

    //导出子订单2
    List<MallSubOrderExcelVO> queryErpOrderForExcelByIdList(List<Long> idList);

    void updateOuterOrderDetailByItemSku(@Param("erpOrderIdList") List<String> erpOrderIdList);

    List<MallSubOrderDO> selectOuterOrderDetailByOuterOrderId(@Param("outerOrderId") Long outerOrderId);

    void updateUpcForOuterOrderDetail(MallOrderDO erpOrder);

    void updateWeightForOuterOrderDetail(MallOrderDO erpOrder);

    List<MallSubOrderDO> queryByOrderId(List<Long> erpOrderIdList);

	List<MallSubOrderDO> queryByOrderNoList(List<String> erpOrderNoList);

    String selectPositionNoByOrderId(String orderNo);

    List<MallSubOrderDO> selectBatchIds(@Param("list") List<Long> list);

    int selectCount(MallSubOrderDO erpOrder);

    List<MallSubOrderDO> SelectByOrderNo(String orderNo);

    /**
     * 找出已发货的数目
     *
     * @param orderNo
     * @return
     */
    int findAlreadyShipped(String orderNo);

    MallSubOrderDO queryHaihuErpOrders(MallSubOrderDO outerOrderDetail);


    Integer selectSubOrderCount(MallSubOrderDO mallSubOrderDO);

    MallSubOrderDO selectSubOrder(MallSubOrderDO mallSubOrderDO);

    List<MallSubOrderDO> selectSubOrderList(MallSubOrderDO mallSubOrderDO);


    MallSubOrderDO selectBySkuCode(String skuCode);

    MallSubOrderDO selectBySubOrderNo(String subOrderNo);

    List<MallSubOrderDO> list();


    //一键分享，我的订单
    List<MyOrderDTO> queryOrderByShareUserId(@Param("shareUserId") String shareUserId,
                                             @Param("start") int start, @Param("pageSize") int pageSize);

    //一键分享，订单详情
    List<MallSubOrderDO> queryOrderDetailByTime(@Param("shareUserId") String shareUserId,
                                                @Param("shareTime") String shareTime,
                                                @Param("start") int start, @Param("pageSize") int pageSize);

    int selectCountWithStateAndOrderNo(@Param("orderNo") String orderNo, @Param("status") Integer status);

    void updateByIsDel(MallSubOrderVO mallSubOrderVO);

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

    Integer changeStatus(@Param("id") Long id, @Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus);

    /**
     * 根据指定条件分页查询子订单
     *
     * @param mallSubOrderQueryVO
     * @param pageQueryParam
     * @return
     */
    List<MallSubOrderDO> listMallSubOrders(@Param("mallSubOrderQueryVO") MallSubOrderQueryVO mallSubOrderQueryVO,
                                           @Param("pageQueryParam") PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询子订单数目
     *
     * @param mallSubOrderQueryVO
     * @return
     */
    int countMallSubOrders(@Param("mallSubOrderQueryVO") MallSubOrderQueryVO mallSubOrderQueryVO);
}
