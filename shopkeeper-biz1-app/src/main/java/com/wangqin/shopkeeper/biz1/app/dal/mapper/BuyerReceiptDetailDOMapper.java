package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerReceiptDetailDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.BuyerReceiptDetailDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyerReceiptDetailDOMapper {
    int countByExample(BuyerReceiptDetailDOExample example);

    int deleteByExample(BuyerReceiptDetailDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BuyerReceiptDetailDO record);

    int insertSelective(BuyerReceiptDetailDO record);

    List<BuyerReceiptDetailDO> selectByExample(BuyerReceiptDetailDOExample example);

    BuyerReceiptDetailDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BuyerReceiptDetailDO record, @Param("example") BuyerReceiptDetailDOExample example);

    int updateByExample(@Param("record") BuyerReceiptDetailDO record, @Param("example") BuyerReceiptDetailDOExample example);

    int updateByPrimaryKeySelective(BuyerReceiptDetailDO record);

    int updateByPrimaryKey(BuyerReceiptDetailDO record);
}