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
	
	DealerTypeDO selectByPrimaryKey(Long id);
	
    List<DealerTypeDO> list(String companyNo);
    
    List<DealerTypeDO> listNoCompanyNo();
    
    void insert(DealerTypeDO sellerType);
    
    //查找销售类别
    DealerTypeDO findDealerType (Long id);
    
    //修改销售类别
    void update(DealerTypeDO dealerType);
    
    //删除销售类别
    void deleteById(DealerTypeDO dealerType);
    
    int countRelativeDealerType(String typeCode);
    
}
