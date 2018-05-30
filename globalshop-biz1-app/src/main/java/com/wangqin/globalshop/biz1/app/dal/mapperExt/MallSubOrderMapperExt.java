package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


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

    List<MallSubOrderDO> queryByOrderId(List<Long> erpOrderIdList);

	String selectPositionNoByOrderId(String orderNo);

    int selectCount(MallSubOrderDO erpOrder);

	List<MallSubOrderDO> SelectByOrderNo(String orderNo);
}
