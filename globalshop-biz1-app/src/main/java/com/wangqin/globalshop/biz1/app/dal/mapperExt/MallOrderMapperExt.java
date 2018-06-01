package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.MallOrderQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author liuhui
 *
 */
public interface MallOrderMapperExt extends MallOrderDOMapper{
	
	Integer queryErpOrdersCount(MallOrderQueryVO erpOrderQueryVO);
	
	List<MallOrderDO> queryErpOrders(MallOrderQueryVO erpOrderQueryVO);
	
	List<MallOrderDO> queryByErpOrderId(@Param("erpOrderIdList")List<Long> erpOrderIdList);
	
	//List<MallOrderDO> queryByShippingOrderTime(ShippingOrderQueryVO shippingOrderQueryVO);
	
	List<MallOrderDO> queryUnStockUpErpOrders(@Param("skuId")Long skuId);

	List<MallOrderDO> queryTransStockUpErpOrders(@Param("skuId")Long skuId);
	
	List<MallOrderDO> queryErpOrderForExcel(MallOrderQueryVO erpOrderQueryVO);
	
	void updateUpcForErpOrder(MallOrderDO erpOrder);
	
	void updateWeightForErpOrder(MallOrderDO erpOrder);
	
	String selectPositionNoByErpOrderId(@Param("erpOrderId")Long erpOrderId);
	
	MallOrderDO queryHaihuErpOrders(MallSubOrderDO outerOrderDetail);
	
	List<MallOrderDO> queryHaihuShippingNO(@Param("targetNo")String targetNo,@Param("skuCode")String skuCode);
	
	List<MallOrderDO> todaySendOrders(@Param("statusList")List<Integer> statusList);


	MallOrderDO selectByOrderNo(String orderNo);
	List<MallOrderDO> queryByStatus(byte status);

	Integer queryOuterOrdersCount(MallOrderVO outerOrderQueryVO);

	List<MallOrderDO> queryOuterOrders(MallOrderVO outerOrderQueryVO);

	List<MallOrderDO> queryOuterOrderForExcel(MallOrderVO outerOrderQueryVO);

    Integer queryPoCount(MallOrderDO mallOrderDO);

	MallOrderDO queryPo(MallOrderDO mallOrderDO);

	List<MallOrderDO> queryPoList(MallOrderDO mallOrderDO);
}
