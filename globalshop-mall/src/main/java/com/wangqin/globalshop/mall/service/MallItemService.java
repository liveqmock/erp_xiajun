package com.wangqin.globalshop.mall.service;

import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;

import java.util.List;

/**
 * Create by 777 on 2018/6/19
 */
public interface MallItemService {

	public ItemDTO itemqueryshare(String itemCode, String companyNo);

	public List<ItemDTO> queryOneDay(String companyNo);


}
