package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemQrcodeShareDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemQrcodeShareDOMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Create by 777 on 2018/8/7
 */
public interface ItemQrcodeShareDOMapperExt extends ItemQrcodeShareDOMapper {

	String selectPicUrl(@Param("userNo") String userNo, @Param("companyNo") String companyNo,@Param("itemCode") String itemCode);

	ItemQrcodeShareDO selectByShareNo(@Param("shareNo") String shareNo);
	
	//查找指定商品的二维码记录,从ERP系统过来的share_no在整个系统里面唯一
	ItemQrcodeShareDO queryRecordByShareNoAndCompanyNo(@Param("shareNo") String shareNo,@Param("companyNo") String companyNo);
	
	void updatePicUrlByShareNo(@Param("shareNo") String shareNo,@Param("picUrl") String picUrl);
}
