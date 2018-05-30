package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.channel.dal.haihu.OuterOrderDetail;

/**
 * Create by 777 on 2018/5/29
 */
public interface MallSubOrderDOMapperExt extends MallSubOrderDOMapper {

	public void insertBatch(List<MallSubOrderDO> outerOrderDetails);
	public void updateOuterOrderDetailByItemSku(List<String> erpOrderIdList);

	public MallSubOrderDO queryHaihuErpOrders(OuterOrderDetail outerOrderDetail);

	public List<MallSubOrderDO> queryPoList(MallSubOrderDO mallSubOrderDO);

}
