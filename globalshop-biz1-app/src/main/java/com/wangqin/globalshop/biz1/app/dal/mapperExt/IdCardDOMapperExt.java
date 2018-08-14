package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.IdCardDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.IdCardDOMapper;
import org.apache.ibatis.annotations.Param;

/**
 * Create by 777 on 2018/8/14
 */
public interface IdCardDOMapperExt extends IdCardDOMapper {

	IdCardDO queryByNameAndIdNum(@Param("name") String name,@Param("idNumber") String idNumber);
}
