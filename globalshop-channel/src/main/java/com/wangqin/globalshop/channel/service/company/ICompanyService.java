package com.wangqin.globalshop.channel.service.company;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

import java.util.List;

/**
 *
 * Company 表数据服务层接口
 *
 */
public interface ICompanyService  {

	public int deleteByPrimaryKey(Long id);

	public int insert(CompanyDO record);

	public int insertSelective(CompanyDO record);

	public CompanyDO selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(CompanyDO record);

	public int updateByPrimaryKeyWithBLOBs(CompanyDO record);

	public int updateByPrimaryKey(CompanyDO record);


	public List<CompanyDO> queryPoList(CompanyDO company);


}
