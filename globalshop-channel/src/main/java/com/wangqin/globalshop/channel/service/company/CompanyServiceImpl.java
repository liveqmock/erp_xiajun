package com.wangqin.globalshop.channel.service.company;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CompanyDOMapperExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Company 表数据服务层接口实现类
 *
 */
@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Resource CompanyDOMapperExt companyDOMapperExt;

	public int deleteByPrimaryKey(Long id){
		return companyDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(CompanyDO record){
		return companyDOMapperExt.insert(record);
	}

	public int insertSelective(CompanyDO record){
		return companyDOMapperExt.insertSelective(record);
	}

	public CompanyDO selectByPrimaryKey(Long id){
		return companyDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(CompanyDO record){
		return companyDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(CompanyDO record){
		return companyDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(CompanyDO record){
		return companyDOMapperExt.updateByPrimaryKey(record);
	}


	public List<CompanyDO> queryPoList(CompanyDO company){
		return companyDOMapperExt.queryPoList(company);
	}


}
