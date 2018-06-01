package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.MallOrderQueryVO;

/**
 * 
 * @author liuhui
 *
 */
public interface CAMallOrderMapperExt extends MallOrderDOMapper{
	public Integer queryPoCount(MallOrderDO mallOrderDO);

	public MallOrderDO queryPo(MallOrderDO mallOrderDO);

	public List<MallOrderDO> queryPoList(MallOrderDO mallOrderDO);

}
