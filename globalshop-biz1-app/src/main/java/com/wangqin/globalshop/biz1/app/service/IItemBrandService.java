package com.wangqin.globalshop.biz1.app.service;


import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;

/**
 * Created by Patrick on 2018/5/15.
 */


public interface IItemBrandService extends ISuperService<ItemBrandDO>{


    void add(ItemBrandDO itemBrand);
      
    
}
