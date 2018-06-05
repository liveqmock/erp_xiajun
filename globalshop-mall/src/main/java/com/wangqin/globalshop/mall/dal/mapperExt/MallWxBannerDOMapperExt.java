package com.wangqin.globalshop.mall.dal.mapperExt;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.mapper.MallWxBannerDOMapper;

public interface MallWxBannerDOMapperExt extends MallWxBannerDOMapper {

    List<MallWxBannerDOMapper> selectByCompanyNo(@Param("companyNo") String companyNo);

}
