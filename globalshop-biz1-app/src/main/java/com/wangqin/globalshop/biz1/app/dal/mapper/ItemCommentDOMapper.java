package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCommentDO;

public interface ItemCommentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemCommentDO record);

    int insertSelective(ItemCommentDO record);

    ItemCommentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemCommentDO record);

    int updateByPrimaryKey(ItemCommentDO record);
}