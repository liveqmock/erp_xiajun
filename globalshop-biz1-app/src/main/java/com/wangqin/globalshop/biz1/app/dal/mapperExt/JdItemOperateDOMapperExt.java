package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdItemOperateDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
public interface JdItemOperateDOMapperExt extends JdItemOperateDOMapper {

	public JdItemOperateDO searchJdItemOperate(JdItemOperateDO jdItemOperateDO);

	public List<JdItemOperateDO> searchJdItemOperateList(JdItemOperateDO jdItemOperateDO);

	public Long searchJdItemOperateCount(JdItemOperateDO jdItemOperateDO);
	
}
