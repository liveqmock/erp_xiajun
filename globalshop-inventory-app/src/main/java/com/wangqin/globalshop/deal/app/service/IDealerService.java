package com.wangqin.globalshop.deal.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerDO;

import java.util.List;

/**
 * 
 * Title: ISellerService.java
 * Description: 
 *
 * @author jc
 * Mar 18, 2017
 *
 */

public interface IDealerService{

    List<DealerDO> list();

    void insert(DealerDO seller);

    DealerDO selectByCode(String dealerCode);
    
    
    //软删除销售
    void deleteByDealer(DealerDO seller);
    
    DealerDO selectByPrimaryKey(Long id);
    
    void updateByDealer(DealerDO seller);
}
