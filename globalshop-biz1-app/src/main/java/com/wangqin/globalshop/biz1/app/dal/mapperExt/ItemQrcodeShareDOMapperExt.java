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
}
