package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.MallOrderQueryVO;

/**
 * 
 * @author liuhui
 *
 */
public interface MallOrderMapperExt extends MallOrderDOMapper{
	
	Integer queryErpOrdersCount(MallOrderQueryVO erpOrderQueryVO);
	
	List<MallOrderDO> queryErpOrders(MallOrderQueryVO erpOrderQueryVO);
	
	List<MallOrderDO> queryByErpOrderId(@Param("erpOrderIdList") List<Long> erpOrderIdList);

	//List<MallOrderDO> queryByShippingOrderTime(ShippingOrderQueryVO shippingOrderQueryVO);

	List<MallOrderDO> queryUnStockUpErpOrders(@Param("skuId") Long skuId);

	List<MallOrderDO> queryTransStockUpErpOrders(@Param("skuId") Long skuId);

	List<MallOrderDO> queryErpOrderForExcel(MallOrderQueryVO erpOrderQueryVO);

	void updateUpcForErpOrder(MallOrderDO erpOrder);

	void updateWeightForErpOrder(MallOrderDO erpOrder);

	String selectPositionNoByErpOrderId(@Param("erpOrderId") Long erpOrderId);

	MallOrderDO queryHaihuErpOrders(MallSubOrderDO outerOrderDetail);

	List<MallOrderDO> queryHaihuShippingNO(@Param("targetNo") String targetNo, @Param("skuCode") String skuCode);

	List<MallOrderDO> todaySendOrders(@Param("statusList") List<Integer> statusList);
}
