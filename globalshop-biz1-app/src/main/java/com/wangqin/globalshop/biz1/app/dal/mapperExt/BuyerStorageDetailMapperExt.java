package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerStorageDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.BuyerStorageDetailDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BuyerStorageDetailMapperExt extends BuyerStorageDetailDOMapper{

	List<BuyerStorageDetailDO> queryPurStoDetails(@Param(value="storageId") Long storageId);
	
	void updateUpcForPurchaseStorageDetail(MallOrderDO erpOrder);

	List<BuyerStorageDetailDO> queryYesterdayPurStoDetail();

	List<BuyerStorageDetailDO> searchList(BuyerStorageDetailDO detailDO);

	BuyerStorageDetailDO search(BuyerStorageDetailDO detailDO);

	Long searchCount(BuyerStorageDetailDO detailDO);
}
