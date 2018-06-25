package com.wangqin.globalshop.purchase.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
public interface IBuyerService {

    List<BuyerDO> list();

    public int deleteByPrimaryKey(Long id);

    public int insert(BuyerDO record);

    public int insertSelective(BuyerDO record);

    public BuyerDO selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(BuyerDO record);

    public int updateByPrimaryKey(BuyerDO record);
}
