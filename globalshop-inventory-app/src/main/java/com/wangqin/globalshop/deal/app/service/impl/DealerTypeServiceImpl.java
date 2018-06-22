package com.wangqin.globalshop.deal.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.DealerTypeDOMapperExt;
import com.wangqin.globalshop.deal.app.service.IDealerTypeService;

/**
 * @author biscuit
 * @data 2018/06/05
 */
@Service
@Authenticated
public class DealerTypeServiceImpl implements IDealerTypeService {
    @Autowired
    private DealerTypeDOMapperExt mapper;
    @Override
	public DealerTypeDO selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}
    @Override
    public List<DealerTypeDO> list() {
        return mapper.list();
    }

    @Override
    public void insert(DealerTypeDO sellerType) {
        sellerType.init();
        mapper.insertSelective(sellerType);
    }

	@Override
	public DealerTypeDO findDealerType(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(DealerTypeDO dealerType) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(dealerType);
	}

	@Override
	public void deleteById(DealerTypeDO dealerType) {
		// TODO Auto-generated method stub
		DealerTypeDO dealerTypes = mapper.selectByPrimaryKey(dealerType.getId());
		dealerTypes.setIsDel(true);
		mapper.updateByPrimaryKeySelective(dealerTypes);
	}
	@Override
	public int countRelativeDealerType(String typeCode) {
		// TODO Auto-generated method stub
		return mapper.countRelativeDealerType(typeCode);
	}

	
}
