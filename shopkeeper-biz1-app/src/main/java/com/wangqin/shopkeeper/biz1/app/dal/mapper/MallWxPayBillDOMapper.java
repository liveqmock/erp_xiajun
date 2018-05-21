package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallWxPayBillDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.MallWxPayBillDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallWxPayBillDOMapper {
    int countByExample(MallWxPayBillDOExample example);

    int deleteByExample(MallWxPayBillDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallWxPayBillDO record);

    int insertSelective(MallWxPayBillDO record);

    List<MallWxPayBillDO> selectByExample(MallWxPayBillDOExample example);

    MallWxPayBillDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallWxPayBillDO record, @Param("example") MallWxPayBillDOExample example);

    int updateByExample(@Param("record") MallWxPayBillDO record, @Param("example") MallWxPayBillDOExample example);

    int updateByPrimaryKeySelective(MallWxPayBillDO record);

    int updateByPrimaryKey(MallWxPayBillDO record);
}