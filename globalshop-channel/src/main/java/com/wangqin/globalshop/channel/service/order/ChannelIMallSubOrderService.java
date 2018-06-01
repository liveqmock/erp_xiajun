package com.wangqin.globalshop.channel.service.order;

import com.wangqin.globalshop.channel.Exception.InventoryException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.common.utils.BizResult;

import java.util.List;

/**
 *
 * MallSubOrder 表数据服务层接口
 *
 */
public interface ChannelIMallSubOrderService {

	public void splithaihuErpOrder(String outerOrderDetailList, String channelOrderNo);


	BizResult lockErpOrder(MallSubOrderDO erpOrder)throws InventoryException;


	public int deleteByPrimaryKey(Long id);

	public int insert(MallSubOrderDO record);

	public int insertSelective(MallSubOrderDO record);

	public MallSubOrderDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(MallSubOrderDO record);

	public int updateByPrimaryKey(MallSubOrderDO record);

	public void insertBatch(List<MallSubOrderDO> outerOrderDetails);


}
