package com.wangqin.globalshop.mall.dal.mapperExt;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxSpecialPageDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallWxSpecialPageDOMapper;

public interface MallWxSpecialPageDOMapperExt extends MallWxSpecialPageDOMapper {

    MallWxSpecialPageDO selectBySpecialPageId(@Param("specialPageId") Long specialPageId);

}
