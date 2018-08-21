package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;

import java.util.List;

/**
 * Create by 777 on 2018/8/20
 */
public interface JdLogisticsService {

	int deleteByPrimaryKey(Long id);

	int insert(JdLogisticsDO record);

	int insertSelective(JdLogisticsDO record);

	JdLogisticsDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(JdLogisticsDO record);

	int updateByPrimaryKey(JdLogisticsDO record);

	public JdLogisticsDO queryPo(JdLogisticsDO record);

	public Long queryPoCount(JdLogisticsDO record);

	public List<JdLogisticsDO> queryPoList(JdLogisticsDO record);

	void dealLogistics(ChannelShopDO channelShop, List<MallSubOrderDO> mallSubOrderDOS, ShippingOrderDO shippingOrder);
}
