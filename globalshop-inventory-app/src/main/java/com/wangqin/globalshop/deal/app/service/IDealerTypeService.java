package com.wangqin.globalshop.deal.app.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.DealerTypeDO;

import java.util.List;

/**
 * 
 * Title: ISellerTypeService.java
 * Description: 
 *
 * @author jc
 * Mar 17, 2017
 *
 */

public interface IDealerTypeService {

    List<DealerTypeDO> list();

    void insert(DealerTypeDO sellerType);
}
