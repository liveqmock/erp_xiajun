package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;


/**
 * 商品 数据控制层
 * @author zhulu
 *
 */
public interface MallSubOrderMapperExt extends MallSubOrderDOMapper{

	void updateOuterOrderDetailByItemSku(@Param("erpOrderIdList")List<Long> erpOrderIdList);
	
	List<MallSubOrderDO> selectOuterOrderDetailByOuterOrderId(@Param("outerOrderId") Long outerOrderId);
	
	void updateUpcForOuterOrderDetail(MallOrderDO erpOrder);
	
	void updateWeightForOuterOrderDetail(MallOrderDO erpOrder);
}
