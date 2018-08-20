package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdLogisticsDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/8/20
 */
public interface JdLogisticsDOMapperExt extends JdLogisticsDOMapper {

	public JdLogisticsDO queryPo(JdLogisticsDO record);

	public Long queryPoCount(JdLogisticsDO record);

	public List<JdLogisticsDO> queryPoList(JdLogisticsDO record);

}
