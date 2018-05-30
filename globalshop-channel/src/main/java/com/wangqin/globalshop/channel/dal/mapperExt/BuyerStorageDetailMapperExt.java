package com.wangqin.globalshop.channel.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerStorageDetailDOMapper;

public interface BuyerStorageDetailMapperExt extends BuyerStorageDetailDOMapper{
	List<BuyerStorageDetailDO> queryPurStoDetails(@Param(value = "storageId") Long storageId);
	
	void updateUpcForPurchaseStorageDetail(MallOrderDO erpOrder);

	List<BuyerStorageDetailDO> queryYesterdayPurStoDetail();
}
