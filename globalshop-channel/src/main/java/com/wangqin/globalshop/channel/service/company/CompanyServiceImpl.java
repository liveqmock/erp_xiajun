package com.wangqin.globalshop.channel.service.company;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CompanyDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired CompanyDOMapperExt companyDOMapperExt;

	@Override
    public int deleteByPrimaryKey(Long id){
		return companyDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(CompanyDO record){
		return companyDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(CompanyDO record){
		return companyDOMapperExt.insertSelective(record);
	}

	@Override
    public CompanyDO selectByPrimaryKey(Long id){
		return companyDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(CompanyDO record){
		return companyDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKeyWithBLOBs(CompanyDO record){
		return companyDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
    public int updateByPrimaryKey(CompanyDO record){
		return companyDOMapperExt.updateByPrimaryKey(record);
	}


	@Override
    public List<CompanyDO> queryPoList(CompanyDO company){
		return companyDOMapperExt.queryPoList(company);
	}


}
