package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.biz1.app.dto.MyOrderDTO;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品 数据控制层
 * @author zhulu
 *
 */
public interface MallSubOrderMapperExt extends MallSubOrderDOMapper{
	@Override
    int deleteByPrimaryKey(Long id);

	@Override
    int insertSelective(MallSubOrderDO record);

	@Override
    MallSubOrderDO selectByPrimaryKey(Long id);


	List<MallSubOrderDO> selectList(MallSubOrderDO order);


	List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO);


	List<MallSubOrderDO> selectUnClosedByOrderNo(String orderNo);

	Integer queryErpOrdersCount(MallSubOrderVO erpOrderQueryVO);

	List<MallSubOrderDO> queryErpOrders(MallSubOrderVO erpOrderQueryVO);

	List<MallSubOrderDO> selectByOrderNo(String orderNo);

	List<MallSubOrderDO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO);
	void updateOuterOrderDetailByItemSku(@Param("erpOrderIdList")List<String> erpOrderIdList);

	List<MallSubOrderDO> selectOuterOrderDetailByOuterOrderId(@Param("outerOrderId") Long outerOrderId);

	void updateUpcForOuterOrderDetail(MallOrderDO erpOrder);

	void updateWeightForOuterOrderDetail(MallOrderDO erpOrder);

    List<MallSubOrderDO> queryByOrderId(List<Long> erpOrderIdList);

	String selectPositionNoByOrderId(String orderNo);

	List<MallSubOrderDO> selectBatchIds(@Param("list") List<Long> list);
    int selectCount(MallSubOrderDO erpOrder);

	List<MallSubOrderDO> SelectByOrderNo(String orderNo);

	/**
	 * 找出已发货的数目
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
    List<MyOrderDTO> queryOrderByShareUserId(@Param("shareUserId")String shareUserId,
    		@Param("start")int start,@Param("pageSize")int pageSize);
    
    //一键分享，订单详情
    List<MallSubOrderDO> queryOrderDetailByTime(@Param("shareUserId")String shareUserId,
    		@Param("shareTime")String shareTime,
    		@Param("start")int start,@Param("pageSize")int pageSize);

}
