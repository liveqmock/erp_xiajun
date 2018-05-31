package com.wangqin.globalshop.channel.service.order;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;

import java.util.List;

/**
 *
 * MallOrder 表数据服务层接口
 *
 */
public interface IMallOrderService  {

	public int deleteByPrimaryKey(Long id);

	public int insert(MallOrderDO record);

	public int insertSelective(MallOrderDO record);

	public MallOrderDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(MallOrderDO record);

	public int updateByPrimaryKey(MallOrderDO record);


	public Integer queryPoCount(MallOrderDO mallOrderDO);

	public MallOrderDO queryPo(MallOrderDO mallOrderDO);

	public List<MallOrderDO> queryPoList(MallOrderDO mallOrderDO);


	public void review(String orderNo);

	public void reviewByIdList(List<String> orderNoList);


}
