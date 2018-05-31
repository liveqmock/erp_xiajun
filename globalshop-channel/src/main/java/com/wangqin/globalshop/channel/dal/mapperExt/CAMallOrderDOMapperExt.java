package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;

/**
 * Create by 777 on 2018/5/29
 */
public interface CAMallOrderDOMapperExt extends MallOrderDOMapper {

	public Integer queryPoCount(MallOrderDO mallOrderDO);

	public MallOrderDO queryPo(MallOrderDO mallOrderDO);

	public List<MallOrderDO> queryPoList(MallOrderDO mallOrderDO);



}
