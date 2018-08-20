package com.wangqin.globalshop.channel.service.order;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;

import java.util.List;

/**
 *
 * MallSubOrder 表数据服务层接口
 *
 */
public interface ChannelIMallSubOrderService {

//	void splithaihuErpOrder(String outerOrderDetailList, String channelOrderNo, ChannelAccountDO channelAccount) throws InventoryException;


//	lt lockErpOrder(MallSubOrderDO erpOrder)throws InventoryException;


	int deleteByPrimaryKey(Long id);

	int insert(MallSubOrderDO record);

	int insertSelective(MallSubOrderDO record);

	MallSubOrderDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MallSubOrderDO record);

	int updateByPrimaryKey(MallSubOrderDO record);

	void insertBatch(List<MallSubOrderDO> outerOrderDetails);

	void updateOuterOrderDetailByItemSku(List<String> outOrderIdList);
}
