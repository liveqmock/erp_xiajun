package com.wangqin.globalshop.deal.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.DealerTypeDOMapperExt;
import com.wangqin.globalshop.deal.app.service.IDealerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/05
 */
@Service
public class DealerTypeServiceImpl implements IDealerTypeService {
    @Autowired
    private DealerTypeDOMapperExt mapper;
    @Override
    public List<DealerTypeDO> list() {
        return mapper.list();
    }

    @Override
    public void insert(DealerTypeDO sellerType) {
        sellerType.init();
        mapper.insertSelective(sellerType);
    }
}
