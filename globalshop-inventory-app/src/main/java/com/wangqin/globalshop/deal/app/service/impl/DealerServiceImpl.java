package com.wangqin.globalshop.deal.app.service.impl;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.DealerDOMapperExt;
import com.wangqin.globalshop.deal.app.service.IDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author biscuit
 * @data 2018/06/05
 */
@Service
@Authenticated
public class DealerServiceImpl implements IDealerService {
    @Autowired
    private DealerDOMapperExt mapper;
    @Override
    public List<Map<String, String>> dealerList(String companyNo) {
        return mapper.dealerList(companyNo);
    }
    
    @Override
    public void insert(DealerDO seller) {
        seller.init();
        mapper.insertSelective(seller);
    }

    @Override
    public DealerDO selectByCode(String code) {
        return mapper.selectByCode(code);
    }

	@Override
	public DealerDO selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByDealer(DealerDO seller) {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(seller.getId());
	}

	@Override
	public void updateByDealer(DealerDO seller) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(seller);
		
	}

	
}
