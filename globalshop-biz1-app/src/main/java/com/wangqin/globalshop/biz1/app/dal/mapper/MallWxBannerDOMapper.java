package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxBannerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxBannerDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallWxBannerDOMapper {
    int countByExample(MallWxBannerDOExample example);

    int deleteByExample(MallWxBannerDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallWxBannerDO record);

    int insertSelective(MallWxBannerDO record);

    List<MallWxBannerDO> selectByExample(MallWxBannerDOExample example);

    MallWxBannerDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallWxBannerDO record, @Param("example") MallWxBannerDOExample example);

    int updateByExample(@Param("record") MallWxBannerDO record, @Param("example") MallWxBannerDOExample example);

    int updateByPrimaryKeySelective(MallWxBannerDO record);

    int updateByPrimaryKey(MallWxBannerDO record);
}