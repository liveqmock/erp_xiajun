package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerReceiptDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerReceiptDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerReceiptDOMapper {
    int countByExample(BuyerReceiptDOExample example);

    int deleteByExample(BuyerReceiptDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerReceiptDO record);

    int insertSelective(BuyerReceiptDO record);

    List<BuyerReceiptDO> selectByExample(BuyerReceiptDOExample example);

    BuyerReceiptDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerReceiptDO record, @Param("example") BuyerReceiptDOExample example);

    int updateByExample(@Param("record") BuyerReceiptDO record, @Param("example") BuyerReceiptDOExample example);

    int updateByPrimaryKeySelective(BuyerReceiptDO record);

    int updateByPrimaryKey(BuyerReceiptDO record);
}