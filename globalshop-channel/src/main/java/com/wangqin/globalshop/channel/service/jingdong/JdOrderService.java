package com.wangqin.globalshop.channel.service.jingdong;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;

import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
public interface JdOrderService {

	public int deleteByPrimaryKey(Long id);

	public int insert(JdOrderDO record);

	public int insertSelective(JdOrderDO record);

	public JdOrderDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(JdOrderDO record);

	public int updateByPrimaryKeyWithBLOBs(JdOrderDO record);

	public int updateByPrimaryKey(JdOrderDO record);

	public JdOrderDO searchJdOrder(JdOrderDO jdOrderDO);

	public List<JdOrderDO> searchJdOrderList(JdOrderDO jdOrderDO);

	public Long searchJdOrderCount(JdOrderDO jdOrderDO);

	public void saveOrders4Task(List<JdOrderDO> jdOrderDOS);

	public void sendJdOrder2globalshop4Task(JdOrderDO jdOrderDO, JdShopOauthDO shopOauth);
}
