package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdItemDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/13
 */
public interface JdItemDOMapperExt extends JdItemDOMapper {

	public JdItemDO searchJdItem(JdItemDO jdItemDO);

	public List<JdItemDO> searchJdItemList(JdItemDO jdItemDO);

	public Long searchJdItemCount(JdItemDO jdItemDO);
}
