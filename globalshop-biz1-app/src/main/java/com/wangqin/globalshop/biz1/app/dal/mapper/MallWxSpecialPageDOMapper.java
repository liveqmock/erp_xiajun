package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxSpecialPageDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxSpecialPageDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallWxSpecialPageDOMapper {
    int countByExample(MallWxSpecialPageDOExample example);

    int deleteByExample(MallWxSpecialPageDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallWxSpecialPageDO record);

    int insertSelective(MallWxSpecialPageDO record);

    List<MallWxSpecialPageDO> selectByExample(MallWxSpecialPageDOExample example);

    MallWxSpecialPageDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallWxSpecialPageDO record, @Param("example") MallWxSpecialPageDOExample example);

    int updateByExample(@Param("record") MallWxSpecialPageDO record, @Param("example") MallWxSpecialPageDOExample example);

    int updateByPrimaryKeySelective(MallWxSpecialPageDO record);

    int updateByPrimaryKey(MallWxSpecialPageDO record);
}