package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品 数据控制层
 * @author zhulu
 *
 */
public interface MallSubOrderMapperExt extends MallSubOrderDOMapper{
	int deleteByPrimaryKey(Long id);

	int insertSelective(MallSubOrderDO record);

	MallSubOrderDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MallSubOrderDO record);

	int updateByPrimaryKey(MallSubOrderDO record);

	List<MallSubOrderDO> selectList(MallSubOrderDO order);

	List<MallSubOrderDO> selectBatchIds(List<Long> batchIds);

	List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO);


	List<MallSubOrderDO> selectUnClosedByOrderNo(String orderNo);

	Integer queryErpOrdersCount(MallSubOrderVO erpOrderQueryVO);

	List<MallSubOrderDO> queryErpOrders(MallSubOrderVO erpOrderQueryVO);

	List<MallSubOrderDO> selectByOrderNo(String orderNo);

	List<MallSubOrderDO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO);
	void updateOuterOrderDetailByItemSku(@Param("erpOrderIdList")List<Long> erpOrderIdList);
	
	List<MallSubOrderDO> selectOuterOrderDetailByOuterOrderId(@Param("outerOrderId") Long outerOrderId);
	
	void updateUpcForOuterOrderDetail(MallOrderDO erpOrder);
	
	void updateWeightForOuterOrderDetail(MallOrderDO erpOrder);

    List<MallSubOrderDO> queryByOrderId(List<Long> erpOrderIdList);

	String selectPositionNoByOrderId(String orderNo);

    int selectCount(MallSubOrderDO erpOrder);

	List<MallSubOrderDO> SelectByOrderNo(String orderNo);

	/**
	 * 找出已发货的数目
	 * @param orderNo
	 * @return
	 */
	int findAlreadyShipped(String orderNo);
}
