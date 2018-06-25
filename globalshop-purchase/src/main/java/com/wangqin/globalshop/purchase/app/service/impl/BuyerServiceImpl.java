package com.wangqin.globalshop.purchase.app.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerDOMapperExt;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.purchase.app.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class BuyerServiceImpl implements IBuyerService {
    @Autowired
    private BuyerDOMapperExt mapper;
    @Override
    public List<BuyerDO> list() {
        return mapper.list(AppUtil.getLoginUserCompanyNo());
    }



    @Override
    public int deleteByPrimaryKey(Long id){
        return mapper.deleteByPrimaryKey(id);
    }
    @Override
    public int insert(BuyerDO record){
        return mapper.insert(record);
    }
    @Override
    public int insertSelective(BuyerDO record){
        return mapper.insertSelective(record);
    }
    @Override
    public BuyerDO selectByPrimaryKey(Long id){
        return mapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(BuyerDO record){
        return mapper.updateByPrimaryKeySelective(record);
    }
    @Override
    public int updateByPrimaryKey(BuyerDO record){
        return mapper.updateByPrimaryKey(record);
    }
}
