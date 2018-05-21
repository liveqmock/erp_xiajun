package com.wangqin.shopkeeper.biz1.app.dal.mapper;

import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemCommentDO;
import com.wangqin.shopkeeper.biz1.app.dal.dataObject.ItemCommentDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemCommentDOMapper {
    int countByExample(ItemCommentDOExample example);

    int deleteByExample(ItemCommentDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemCommentDO record);

    int insertSelective(ItemCommentDO record);

    List<ItemCommentDO> selectByExample(ItemCommentDOExample example);

    ItemCommentDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemCommentDO record, @Param("example") ItemCommentDOExample example);

    int updateByExample(@Param("record") ItemCommentDO record, @Param("example") ItemCommentDOExample example);

    int updateByPrimaryKeySelective(ItemCommentDO record);

    int updateByPrimaryKey(ItemCommentDO record);
}