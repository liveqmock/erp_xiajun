package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdOrderDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/12
 */
public interface JdOrderDOMapperExt extends JdOrderDOMapper {

	public JdOrderDO searchJdOrder(JdOrderDO jdOrderDO);

	public List<JdOrderDO> searchJdOrderList(JdOrderDO jdOrderDO);

	public Long searchJdOrderCount(JdOrderDO jdOrderDO);
}
